package com.twitter.meil_mitu.twitter4hk.api.account

import com.twitter.meil_mitu.twitter4hk.AbsAPI
import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.converter.api.IAccountConverter

import java.io.File

class AccountAPI<TSetting, TUser>(
        oauth: AbsOauth,
        protected val json: IAccountConverter<TSetting, TUser>) : AbsAPI(oauth) {

    val settings: GetSettings<TSetting>
        get() = GetSettings(oauth, json)

    fun verifyCredentials(): VerifyCredentials<TUser> {
        return VerifyCredentials(oauth, json)
    }

    fun postSettings(): PostSettings<TSetting> {
        return PostSettings(oauth, json)
    }

    fun updateDeliveryDevice(device: String): UpdateDeliveryDevice {
        return UpdateDeliveryDevice(oauth, json, device)
    }

    fun updateProfile(): UpdateProfile<TUser> {
        return UpdateProfile(oauth, json)
    }

    fun updateProfileBackgroundImage(): UpdateProfileBackgroundImage<TUser> {
        return UpdateProfileBackgroundImage(oauth, json)
    }

    fun updateProfileImage(image: File): UpdateProfileImage<TUser> {
        return UpdateProfileImage(oauth, json, image)
    }

    fun removeProfileBanner(): RemoveProfileBanner {
        return RemoveProfileBanner(oauth, json)
    }

    fun updateProfileBanner(banner: File): UpdateProfileBanner {
        return UpdateProfileBanner(oauth, json, banner)
    }

}
