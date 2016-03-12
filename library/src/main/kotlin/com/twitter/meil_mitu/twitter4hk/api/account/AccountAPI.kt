package com.twitter.meil_mitu.twitter4hk.api.account

import com.twitter.meil_mitu.twitter4hk.AbsAPI
import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.converter.api.IAccountConverter

import java.io.File

class AccountAPI<TSetting, TUser>(
        oauth: AbsOauth,
        protected val json: IAccountConverter<TSetting, TUser>) : AbsAPI(oauth) {

    fun getSettings() = GetSettings(oauth, json)

    fun verifyCredentials() = VerifyCredentials(oauth, json)

    fun postSettings() = PostSettings(oauth, json)

    fun updateDeliveryDevice(device: String) = UpdateDeliveryDevice(oauth, json, device)

    fun updateProfile() = UpdateProfile(oauth, json)

    fun updateProfileBackgroundImage() = UpdateProfileBackgroundImage(oauth, json)

    fun updateProfileImage(image: File) = UpdateProfileImage(oauth, json, image)

    fun removeProfileBanner() = RemoveProfileBanner(oauth, json)

    fun updateProfileBanner(banner: File) = UpdateProfileBanner(oauth, json, banner)

}
