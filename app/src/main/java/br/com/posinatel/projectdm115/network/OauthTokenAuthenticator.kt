package br.com.posinatel.projectdm115.network

import android.util.Log
import br.com.posinatel.projectdm115.util.SharedPreferencesUtils
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

private const val TAG = "OauthTokenAuthenticator"

class OauthTokenAuthenticator() : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        val token = retrieveNewToken()
        SharedPreferencesUtils.saveToken(token.accessToken, token.expiresIn)
        return response.request().newBuilder()
            .header("Authorization", "Bearer ${token.accessToken}")
            .build()
    }

    private fun retrieveNewToken(): OauthTokenResponse {
        Log.i(TAG, "Retrieving new token")
        return SalesApi.retrofitService.getToken(
            "Basic c2llY29sYTptYXRpbGRl",
            "password",
            "matilde@siecola.com.br",
            "matilde"
        ).execute().body()!!
    }


}