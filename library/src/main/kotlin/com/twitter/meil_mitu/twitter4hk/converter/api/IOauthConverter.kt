package com.twitter.meil_mitu.twitter4hk.converter.api

import com.twitter.meil_mitu.twitter4hk.converter.IOauthRequestTokenConverter
import com.twitter.meil_mitu.twitter4hk.converter.IOauthTokenConverter
import com.twitter.meil_mitu.twitter4hk.data.OauthRequestToken
import com.twitter.meil_mitu.twitter4hk.data.OauthToken

interface IOauthConverter :
        IOauthRequestTokenConverter<OauthRequestToken>,
        IOauthTokenConverter<OauthToken> {
}