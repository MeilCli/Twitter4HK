package com.twitter.meil_mitu.twitter4hk.streaming.user

interface IUserStreamListener<TDirectMessage, TStatus, TUser, TUserList> {

    fun onUnknown(line: String)

    fun onStatus(status: TStatus)

    fun onDeleteStatus(userId: Long, id: Long)

    fun onFriends(friends: LongArray)

    fun onDirectMessage(directMessage: TDirectMessage)

    fun onBlock(user: TUser, blockedUser: TUser)

    fun onUnBlock(user: TUser, unblockedUser: TUser)

    fun onFavorite(user: TUser, favoritedUser: TUser, status: TStatus)

    fun onUnFavorite(user: TUser, unfavoritedUser: TUser, status: TStatus)

    fun onFollow(user: TUser, followedUser: TUser)

    fun onUnFollow(user: TUser, unfollowedUser: TUser)

    fun onListCreated(user: TUser, userList: TUserList)

    fun onListDestroyed(user: TUser, userList: TUserList)

    fun onListUpdated(user: TUser, userList: TUserList)

    fun onListMemberAdded(user: TUser, addedUser: TUser, userList: TUserList)

    fun onListMemberRemoved(user: TUser, removedUser: TUser, userList: TUserList)

    fun onListUserSubscribed(user: TUser, ownerUser: TUser, userList: TUserList)

    fun onListUserUnSubscribed(user: TUser, ownerUser: TUser, userList: TUserList)

    fun onUserUpdate(user: TUser)

    fun onMute(user: TUser, mutedUser: TUser)

    fun onUnMute(user: TUser, unmutedUser: TUser)

    fun onFavoritedRetweet(user: TUser, favoritedRetweetingUser: TUser, status: TStatus)

    fun onRetweetedRetweet(user: TUser, retweetedRetweetingUser: TUser, status: TStatus)

    fun onQuotedTweet(user: TUser, quotedUser: TUser, status: TStatus)

}
