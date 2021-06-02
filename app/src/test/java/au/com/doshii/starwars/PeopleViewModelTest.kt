package au.com.doshii.starwars


import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import au.com.doshii.starwars.di.ApiModuleTest
import au.com.doshii.starwars.di.AppModule
import au.com.doshii.starwars.di.DaggerViewModelComponent
import au.com.doshii.starwars.model.*
import au.com.doshii.starwars.viewmodel.PeopleViewModel
import com.android.volley.NetworkResponse
import com.android.volley.ParseError
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.HttpHeaderParser
import com.google.gson.JsonSyntaxException
import junit.framework.TestCase
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.io.UnsupportedEncodingException


class PeopleViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()
    @Mock
    lateinit var baseApi: BaseApi

    val application = Mockito.mock(Application::class.java)
    private var peopleViewModel = TestPeopleViewModel(application)

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)

        val testComponent = DaggerViewModelComponent.builder()
            .appModule(AppModule(application))
            .apiModule(ApiModuleTest(baseApi))
            .build()
            .inject(peopleViewModel)
    }

    @Test
    fun testSuccessfulResponseForPeople() {
       val people = People("TestName","","","","","","",Gender.NA,"",
           arrayListOf(),arrayListOf(),arrayListOf(),arrayListOf(),"","","")
        val peopleList = arrayListOf(people)
        val peopleResult = PeopleResult(0,"2",null,peopleList)
        peopleViewModel.testPeopleResult = peopleResult
        peopleViewModel.refresh()
        Assert.assertEquals(1,peopleViewModel.peopleResult.value?.peoples?.size)
        Assert.assertEquals(false,peopleViewModel.onNoData.value)
    }

    @Test
    fun testFailedResponseForPeople() {
        val peopleResult = PeopleResult(0,"2",null, arrayListOf())
        peopleViewModel.testPeopleResult = peopleResult
        peopleViewModel.refresh()
        peopleViewModel.onErrorResponse(null)
        Assert.assertEquals(null,peopleViewModel.peopleResult.value)
        Assert.assertEquals(true,peopleViewModel.onNoData.value)
    }

    private class TestGsonRequest<PeopleResult>(
       url: String,
       private val clazz: Class<PeopleResult>,
       private val listener: Response.Listener<PeopleResult>,
       errorListener: Response.ErrorListener,
       private val peopleResult: PeopleResult,
       succees:Boolean
   ) : GsonRequest<PeopleResult>(url,clazz,listener,errorListener) {

        init {
            if (succees) {
                deliverResponse(peopleResult)
            }
        }

       override fun deliverResponse(response: PeopleResult) = listener.onResponse(peopleResult)

       override fun parseNetworkResponse(response: NetworkResponse?): Response<PeopleResult> {
           return try {
              Response.success(peopleResult,
                   HttpHeaderParser.parseCacheHeaders(response))
           } catch (e: UnsupportedEncodingException) {
               Response.error(ParseError(e))
           } catch (e: JsonSyntaxException) {
               Response.error(ParseError(e))
           }
       }
   }

    private class TestPeopleViewModel(application: Application):PeopleViewModel(application,true) {

        lateinit var testPeopleResult: PeopleResult
        lateinit var gsonRequest: GsonRequest<PeopleResult>

        fun setPeopleResult(peopleResult: PeopleResult) {
            this.testPeopleResult = peopleResult
        }

        override fun onResponse(response: PeopleResult) {
            super.onResponse(testPeopleResult)
        }

        override fun onErrorResponse(error: VolleyError?) {
            super.onErrorResponse(VolleyError())
        }

        override fun obtainGSonRequest(): TestGsonRequest<PeopleResult> {
            gsonRequest =  TestGsonRequest("",PeopleResult::class.java,this,
                this,testPeopleResult, true)
            return gsonRequest as TestGsonRequest<PeopleResult>
        }
    }
}