package com.twitter.meil_mitu.twitter4hk.data

import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getJSONObject
import org.json.JSONObject

class Banner {

    val ipad: BannerSize
    val ipadRetina: BannerSize
    val web: BannerSize
    val webRetina: BannerSize
    val mobile: BannerSize
    val mobileRetina: BannerSize

    @Throws(Twitter4HKException::class)
    constructor(obj: JSONObject) {
        var _obj = obj
        if (_obj.isNull("sizes")) {
            throw Twitter4HKException("sizes is null")
        } else {
            _obj = getJSONObject(_obj, "sizes")
        }
        if (_obj.isNull("ipad")) {
            throw Twitter4HKException("ipad is null")
        } else {
            ipad = BannerSize(getJSONObject(_obj, "ipad"))
        }
        if (_obj.isNull("ipad_retina")) {
            throw Twitter4HKException("ipad_retina is null")
        } else {
            ipadRetina = BannerSize(getJSONObject(_obj, "ipad_retina"))
        }
        if (_obj.isNull("web")) {
            throw Twitter4HKException("web is null")
        } else {
            web = BannerSize(getJSONObject(_obj, "web"))
        }
        if (_obj.isNull("web_retina")) {
            throw Twitter4HKException("web_retina is null")
        } else {
            webRetina = BannerSize(getJSONObject(_obj, "web_retina"))
        }
        if (_obj.isNull("mobile")) {
            throw Twitter4HKException("mobile is null")
        } else {
            mobile = BannerSize(getJSONObject(_obj, "mobile"))
        }
        if (_obj.isNull("mobile_retina")) {
            throw Twitter4HKException("mobile_retina")
        } else {
            mobileRetina = BannerSize(getJSONObject(_obj, "mobile_retina"))
        }
    }

    override fun toString(): String {
        return "Banner{Ipad=$ipad, IpadRetina=$ipadRetina, Web=$web, WebRetina=$webRetina, Mobile=$mobile, MobileRetina=$mobileRetina}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Banner) return false

        if (ipad != other.ipad) return false
        if (ipadRetina != other.ipadRetina) return false
        if (mobile != other.mobile) return false
        if (mobileRetina != other.mobileRetina) return false
        if (web != other.web) return false
        if (webRetina != other.webRetina) return false

        return true
    }

    override fun hashCode(): Int {
        var result = ipad.hashCode()
        result = 31 * result + ipadRetina.hashCode()
        result = 31 * result + web.hashCode()
        result = 31 * result + webRetina.hashCode()
        result = 31 * result + mobile.hashCode()
        result = 31 * result + mobileRetina.hashCode()
        return result
    }

}
