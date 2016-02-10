package com.twitter.meil_mitu.twitter4hk.converter.api

import com.twitter.meil_mitu.twitter4hk.converter.*

interface IFriendshipsConverter<TCursorIDs, TFriendship, TIDs, TRelationship, TUser> :
        ICursorIDsConverter<TCursorIDs>,
        IFriendshipConverter<TFriendship>,
        IIDsConverter<TIDs>,
        IRelationshipConverter<TRelationship>,
        IUserConverter<TUser> {
}