# Twitter4Holo
Twitter Oauth Library for Android 4.0.3 and upper  
please debug and advice 

* response Objects implements Parcelable without **Support class
* if you want to know Objects, you shall see [data package](https://github.com/MeilCli/Twitter4HK/tree/master/library/src/main/kotlin/com/twitter/meil_mitu/twitter4hk/data)
* Objects is not full
* if you want to use full Objects, you shall make Data class and Converter
* ok [oauth](https://dev.twitter.com/oauth/3-legged)
* ok [oauth2](https://dev.twitter.com/oauth/application-only)
* ok [oauth echo](https://dev.twitter.com/oauth/echo)
* ok [multiple media upload](https://dev.twitter.com/rest/public/uploading-media)
* ok [Video in MediaEntity](https://blog.twitter.com/2015/now-on-twitter-group-direct-messages-and-mobile-video-capture)
* ok [REST API](https://dev.twitter.com/rest/public)
* ok [SampleStream](https://dev.twitter.com/streaming/reference/get/statuses/sample) (please debug and advice)
* ok [FilterStream](https://dev.twitter.com/streaming/reference/post/statuses/filter) (please debug and advice)
* ok [UserStream](https://dev.twitter.com/streaming/reference/get/user) (please debug and advice)
* ok [Aclog API](http://aclog.koba789.com/about/api)

####何をしたかったのか
* Oauth2をわかりやすく
* メソッドをわかりやすく

#### gradle
	repositories {
	    mavenCentral()
	    maven { url "https://raw.github.com/MeilCli/Twitter4Holo/master/library/repository" }
	}
	
	dependencies {
		compile 'com.squareup.okhttp:okhttp:2.7.2'
		compile 'meilcli:twitter4hk:0.0.+@aar'
	}

#### AndroidManifest.xml
	<manifest ...>
	
		<uses-permission android:name="android.permission.INTERNET"/>
	
	</manifest>

#### replace API namespace
*if this endpoint is added another endpoints, may change namespace*

* GET statuses/retweeters/ids → statuses.Retweeters → ??
* GET friendships/no_retweets/ids → friendships.NoRetweets → ??

*endpoints escape, so change namespace*

* GET direct_messages → directmessages.Get
* POST direct_messages/new → directmessages.PostNew
* GET account/settings → account.GetSettings
* POST account/settings → account.PostSettings
* GET users/suggestions/:slug → suggestions.Get
* GET users/suggestions → suggestions.List
* GET users/suggestions/:slug/members → suggestions.Members
* GET lists/subscribers → lists.subscribers.Get
* GET lists/members → lists.members.Get

ライセンス
----------

This source is The MIT License.

using [okhttp](https://github.com/square/okhttp) [Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0)  
using [The Kotlin Standard Library](https://github.com/JetBrains/kotlin/tree/master/libraries/stdlib) [Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0)


