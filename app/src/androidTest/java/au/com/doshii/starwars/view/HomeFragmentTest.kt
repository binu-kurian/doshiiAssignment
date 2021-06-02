package au.com.doshii.starwars.view

import androidx.fragment.app.testing.FragmentScenario
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import au.com.doshii.starwars.R
import au.com.doshii.starwars.view.fragment.HomeFragment
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeFragmentTest  {

    private lateinit var homeFragmentScenario: FragmentScenario<HomeFragment>

    @Before
    fun setUp() {
        homeFragmentScenario = FragmentScenario.Companion.launchInContainer(HomeFragment::class.java)
    }

    @Test
    fun checkIfAllElementsArePresent() {
        onView(withId(R.id.dataOptionList)).check(matches(isDisplayed()))
        onView(withId(R.id.dataOptionList)).perform(RecyclerViewActions.scrollTo<RecyclerView.ViewHolder>(hasDescendant(withText(R.string.people))))
        onView(withId(R.id.dataOptionList)).perform(RecyclerViewActions.scrollTo<RecyclerView.ViewHolder>(hasDescendant(withText(R.string.films))))
        onView(withId(R.id.dataOptionList)).perform(RecyclerViewActions.scrollTo<RecyclerView.ViewHolder>(hasDescendant(withText(R.string.planets))))
        onView(withId(R.id.dataOptionList)).perform(RecyclerViewActions.scrollTo<RecyclerView.ViewHolder>(hasDescendant(withText(R.string.species))))
        onView(withId(R.id.dataOptionList)).perform(RecyclerViewActions.scrollTo<RecyclerView.ViewHolder>(hasDescendant(withText(R.string.starships))))
        onView(withId(R.id.dataOptionList)).perform(RecyclerViewActions.scrollTo<RecyclerView.ViewHolder>(hasDescendant(withText(R.string.vehicles))))
    }

}