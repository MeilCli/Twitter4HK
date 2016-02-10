package com.twitter.meil_mitu.twitter4hk.streaming.user

open class UserStreamListener<TDirectMessage, TStatus, TUser, TUserList> :
        IUserStreamListener<TDirectMessage, TStatus, TUser, TUserList> {


    override fun onUnknown(line: String) {

    }

    override fun onStatus(status: TStatus) {

    }

    override fun onDeleteStatus(userId: Long, id: Long) {

    }

    override fun onFriends(friends: LongArray) {

    }

    override fun onDirectMessage(directMessage: TDirectMessage) {

    }

    override fun onBlock(user: TUser, blockedUser: TUser) {

    }

    override fun onUnBlock(user: TUser, unblockedUser: TUser) {

    }

    override fun onFavorite(user: TUser, favoritedUser: TUser, status: TStatus) {

    }

    override fun onUnFavorite(user: TUser, unfavoritedUser: TUser, status: TStatus) {

    }

    override fun onFollow(user: TUser, followedUser: TUser) {

    }

    override fun onUnFollow(user: TUser, unfollowedUser: TUser) {

    }

    override fun onListCreated(user: TUser, userList: TUserList) {

    }

    override fun onListDestroyed(user: TUser, userList: TUserList) {

    }

    override fun onListUpdated(user: TUser, userList: TUserList) {

    }

    override fun onListMemberAdded(user: TUser, addedUser: TUser, userList: TUserList) {

    }

    override fun onListMemberRemoved(user: TUser, removedUser: TUser, userList: TUserList) {

    }

    override fun onListUserSubscribed(user: TUser, ownerUser: TUser, userList: TUserList) {

    }

    override fun onListUserUnSubscribed(user: TUser, ownerUser: TUser, userList: TUserList) {

    }

    override fun onUserUpdate(user: TUser) {

    }

    override fun onMute(user: TUser, mutedUser: TUser) {

    }

    override fun onUnMute(user: TUser, unmutedUser: TUser) {

    }

    override fun onFavoritedRetweet(user: TUser, favoritedRetweetingUser: TUser, status: TStatus) {

    }

    override fun onRetweetedRetweet(user: TUser, retweetedRetweetingUser: TUser, status: TStatus) {

    }

    override fun onQuotedTweet(user: TUser, quotedUser: TUser, status: TStatus) {

    }

}
