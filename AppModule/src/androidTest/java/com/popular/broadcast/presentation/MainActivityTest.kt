package com.popular.broadcast.presentation


import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.popular.broadcast.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.AllOf.allOf
import org.hamcrest.core.Is.`is`
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun mainActivityTest() {
        val recyclerView = onView(
            allOf(
                withId(R.id.news_rv),
                childAtPosition(
                    withId(R.id.swipe_refresh_layout),
                    0
                )
            )
        )
        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(0, click()))

        val materialButton = onView(
            allOf(
                withId(R.id.open_web_btn), withText("Read from Source"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        1
                    ),
                    8
                )
            )
        )
        materialButton.perform(scrollTo(), click())

        pressBack()

        val recyclerView2 = onView(
            allOf(
                withId(R.id.news_rv),
                childAtPosition(
                    withId(R.id.swipe_refresh_layout),
                    0
                )
            )
        )
        recyclerView2.perform(actionOnItemAtPosition<ViewHolder>(1, click()))

        val materialButton2 = onView(
            allOf(
                withId(R.id.open_web_btn), withText("Read from Source"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        1
                    ),
                    8
                )
            )
        )
        materialButton2.perform(scrollTo(), click())

        pressBack()

        val recyclerView3 = onView(
            allOf(
                withId(R.id.news_rv),
                childAtPosition(
                    withId(R.id.swipe_refresh_layout),
                    0
                )
            )
        )
        recyclerView3.perform(actionOnItemAtPosition<ViewHolder>(17, click()))

        val materialButton3 = onView(
            allOf(
                withId(R.id.open_web_btn), withText("Read from Source"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        1
                    ),
                    8
                )
            )
        )
        materialButton3.perform(scrollTo(), click())

        pressBack()

        val overflowMenuButton = onView(
            allOf(
                withContentDescription("More options"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.action_bar_tool_bar),
                        3
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        overflowMenuButton.perform(click())

        val materialTextView = onView(
            allOf(
                withId(androidx.appcompat.R.id.title), withText("1 day news"),
                childAtPosition(
                    childAtPosition(
                        withId(androidx.appcompat.R.id.content),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        materialTextView.perform(click())

        val recyclerView4 = onView(
            allOf(
                withId(R.id.news_rv),
                childAtPosition(
                    withId(R.id.swipe_refresh_layout),
                    0
                )
            )
        )
        recyclerView4.perform(actionOnItemAtPosition<ViewHolder>(0, click()))

        val materialButton4 = onView(
            allOf(
                withId(R.id.open_web_btn), withText("Read from Source"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        1
                    ),
                    8
                )
            )
        )
        materialButton4.perform(scrollTo(), click())

        pressBack()

        val overflowMenuButton2 = onView(
            allOf(
                withContentDescription("More options"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.action_bar_tool_bar),
                        3
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        overflowMenuButton2.perform(click())

        val materialTextView2 = onView(
            allOf(
                withId(androidx.appcompat.R.id.title), withText("7 days news"),
                childAtPosition(
                    childAtPosition(
                        withId(androidx.appcompat.R.id.content),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        materialTextView2.perform(click())

        val overflowMenuButton3 = onView(
            allOf(
                withContentDescription("More options"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.action_bar_tool_bar),
                        3
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        overflowMenuButton3.perform(click())

        val materialTextView3 = onView(
            allOf(
                withId(androidx.appcompat.R.id.title), withText("30 days news"),
                childAtPosition(
                    childAtPosition(
                        withId(androidx.appcompat.R.id.content),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        materialTextView3.perform(click())

        val recyclerView5 = onView(
            allOf(
                withId(R.id.news_rv),
                childAtPosition(
                    withId(R.id.swipe_refresh_layout),
                    0
                )
            )
        )
        recyclerView5.perform(actionOnItemAtPosition<ViewHolder>(2, click()))

        val materialButton5 = onView(
            allOf(
                withId(R.id.open_web_btn), withText("Read from Source"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        1
                    ),
                    8
                )
            )
        )
        materialButton5.perform(scrollTo(), click())

        pressBack()

        val recyclerView6 = onView(
            allOf(
                withId(R.id.news_rv),
                childAtPosition(
                    withId(R.id.swipe_refresh_layout),
                    0
                )
            )
        )
        recyclerView6.perform(actionOnItemAtPosition<ViewHolder>(0, click()))

        pressBack()

        val appCompatImageView = onView(
            allOf(
                withId(R.id.right_icon),
                childAtPosition(
                    allOf(
                        withId(R.id.action_bar_tool_bar),
                        childAtPosition(
                            withId(R.id.action_bar),
                            0
                        )
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        appCompatImageView.perform(click())

        val appCompatImageView2 = onView(
            allOf(
                withId(R.id.left_icon),
                childAtPosition(
                    allOf(
                        withId(R.id.action_bar_tool_bar),
                        childAtPosition(
                            withId(R.id.action_bar),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatImageView2.perform(click())

        val overflowMenuButton4 = onView(
            allOf(
                withContentDescription("More options"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.action_bar_tool_bar),
                        3
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        overflowMenuButton4.perform(click())

        val materialTextView4 = onView(
            allOf(
                withId(androidx.appcompat.R.id.title), withText("7 days news"),
                childAtPosition(
                    childAtPosition(
                        withId(androidx.appcompat.R.id.content),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        materialTextView4.perform(click())

        val recyclerView7 = onView(
            allOf(
                withId(R.id.news_rv),
                childAtPosition(
                    withId(R.id.swipe_refresh_layout),
                    0
                )
            )
        )
        recyclerView7.perform(actionOnItemAtPosition<ViewHolder>(15, click()))

        val materialButton6 = onView(
            allOf(
                withId(R.id.open_web_btn), withText("Read from Source"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        1
                    ),
                    8
                )
            )
        )
        materialButton6.perform(scrollTo(), click())

        pressBack()

        pressBack()
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
