package com.schiughi.qiitaclient

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.action.ViewActions.replaceText
import android.support.test.espresso.action.ViewActions.closeSoftKeyboard
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.view.View
import android.view.ViewGroup
import com.schiughi.qiitaclient.client.ArticleClient
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.Description
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import rx.Observable

/**
* Created by schiughi on 2017/06/03.
*/
@RunWith(AndroidJUnit4::class)
class MainActivityTest{

    @JvmField
    @Rule
    val activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun 各ビューを表示する_ただしプログレスバーは非表示(){
        onView(withId(R.id.list_view)).check(matches(isDisplayed()))
        onView(withId(R.id.query_edit_text)).check(matches(isDisplayed()))
        onView(withId(R.id.search_button)).check(matches(isDisplayed()))
        onView(withId(R.id.progress_bar)).check(matches(isNotDisplayed()))
    }

    fun isNotDisplayed(): Matcher<View> = not(isDisplayed())

    @Test
    fun `検索ボタンがタップされたら、入力されたクエリ文字列で記事検索APIを叩く`(){
        val searchWord = "user:ngsw_taro"
        val articleClient = mock(ArticleClient::class.java).apply {
            `when`(search(searchWord)).thenReturn(Observable.just(listOf()))
        }
        activityTestRule.activity.articleClient = articleClient

        onView(withId(R.id.query_edit_text)).perform(typeText(searchWord))
        onView(withId(R.id.search_button)).perform(click())
        verify(articleClient).search(searchWord)
    }

    @Test
    fun `一覧画面で記事をタップしたら詳細画面に遷移する`(){
        onView(allOf(withId(R.id.query_edit_text), isDisplayed()))
                .perform(replaceText("user:ngsw_taro"), closeSoftKeyboard())
        onView(allOf(withId(R.id.search_button), withText("search"), isDisplayed()))
                .perform(click())
        onView(allOf(childAtPosition(withId(R.id.list_view), 0), isDisplayed()))
                .perform(click())
    }

    private fun childAtPosition(
            parentMatcher: Matcher<View>, position: Int): Matcher<View> {

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