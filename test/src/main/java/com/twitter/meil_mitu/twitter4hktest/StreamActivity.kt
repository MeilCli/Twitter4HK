package com.twitter.meil_mitu.twitter4hktest

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import com.twitter.meil_mitu.twitter4hk.data.DirectMessage
import com.twitter.meil_mitu.twitter4hk.data.Status
import com.twitter.meil_mitu.twitter4hk.data.User
import com.twitter.meil_mitu.twitter4hk.data.UserList
import com.twitter.meil_mitu.twitter4hk.streaming.IStreamListener
import com.twitter.meil_mitu.twitter4hk.streaming.Stream
import com.twitter.meil_mitu.twitter4hk.streaming.user.IUserStreamListener
import com.twitter.meil_mitu.twitter4hk.streaming.user.UserStream

class StreamActivity : Activity(), IUserStreamListener<DirectMessage, Status, User, UserList>, IStreamListener {

    var stream : Stream? = null
    var textView : TextView?=null
    var handler : Handler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handler= Handler()

        val scrollView = ScrollView(this)
        setContentView(scrollView)
        val linearLayout = LinearLayout(this).apply {
            orientation= LinearLayout.VERTICAL
        }
        scrollView.addView(linearLayout)
        linearLayout.addView(
                Button(this).apply {
                    text="startStream"
                    setOnClickListener {
                        val userStream= twitter.stream.user()
                        userStream.streamListener= this@StreamActivity
                        userStream.userStreamListener=this@StreamActivity
                        userStream.replies= UserStream.RepliesAll
                        userStream.with= UserStream.WithFollowings
                        stream=userStream.call()
                    }
                }
                ,0)
        linearLayout.addView(
                Button(this).apply {
                    text="closeStream"
                    setOnClickListener{
                        stream?.close()
                        stream=null
                    }
                }
                ,1)
        textView= TextView(this)
        linearLayout.addView(textView,2)
    }

    fun show(text:String){
        handler?.post {
            textView?.text= "${textView?.text ?:""} $text \n"
        }
    }

    override fun onException(e: Exception) {
        show("exception: ${e.toString()}")
    }

    override fun onConnect() {
        show("connect: ")
    }

    override fun onDisConnect() {
        show("disconnect: ")
    }

    override fun onClose() {
        show("close: ")
    }

    override fun onStatus(status: Status) {
        show("status: ${status.id}")
    }

    override fun onDeleteStatus(userId: Long, id: Long) {
        show("delete_status: $id")
    }

    override fun onFriends(friends: LongArray) {
        show("friends: ${friends.size}")
    }

    override fun onDirectMessage(directMessage: DirectMessage) {
        show("directmessage: ${directMessage.id}")
    }

    override fun onBlock(user: User, blockedUser: User) {
        show("block: ${user.screenName}")
    }

    override fun onUnBlock(user: User, unblockedUser: User) {
        show("unblock: ${user.screenName}")
    }

    override fun onFavorite(user: User, favoritedUser: User, status: Status) {
        show("favorite: ${user.screenName}")
    }

    override fun onUnFavorite(user: User, unfavoritedUser: User, status: Status) {
        show("unfavorite: ${user.screenName}")
    }

    override fun onFollow(user: User, followedUser: User) {
        show("follow: ${user.screenName}")
    }

    override fun onUnFollow(user: User, unfollowedUser: User) {
        show("unfollow: ${user.screenName}")
    }

    override fun onListCreated(user: User, userList: UserList) {
        show("list_created: ${user.screenName}")
    }

    override fun onListDestroyed(user: User, userList: UserList) {
        show("list_destroyed: ${user.screenName}")
    }

    override fun onListUpdated(user: User, userList: UserList) {
        show("list_updated: ${user.screenName}")
    }

    override fun onListMemberAdded(user: User, addedUser: User, userList: UserList) {
        show("list_member_added: ${user.screenName}")
    }

    override fun onListMemberRemoved(user: User, removedUser: User, userList: UserList) {
        show("list_member_removed: ${user.screenName}")
    }

    override fun onListUserSubscribed(user: User, ownerUser: User, userList: UserList) {
        show("list_user_subscribed: ${user.screenName}")
    }

    override fun onListUserUnSubscribed(user: User, ownerUser: User, userList: UserList) {
        show("list_user_unsubscribed: ${user.screenName}")
    }

    override fun onUserUpdate(user: User) {
        show("user_update: ${user.screenName}")
    }

    override fun onMute(user: User, mutedUser: User) {
        show("mute: ${user.screenName}")
    }

    override fun onUnMute(user: User, unmutedUser: User) {
        show("unmute: ${user.screenName}")
    }

    override fun onFavoritedRetweet(user: User, favoritedRetweetingUser: User, status: Status) {
        show("favorited_retweet: ${user.screenName}")
    }

    override fun onRetweetedRetweet(user: User, retweetedRetweetingUser: User, status: Status) {
        show("retweeted_retweet: ${user.screenName}")
    }

    override fun onQuotedTweet(user: User, quotedUser: User, status: Status) {
        show("quotedtweet: ${user.screenName}")
    }

    override fun onUnknown(line: String) {
        show("unknown: $line")
    }
}