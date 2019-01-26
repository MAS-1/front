package com.pestano.assignment


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


import java.util.Collections

/**
 * Demonstrate authentication using the FirebaseUI-Android library. This activity demonstrates
 * using FirebaseUI for basic email/password sign in.
 *
 * For more information, visit https://github.com/firebase/firebaseui-android
 */
class FirebaseUIActivity : AppCompatActivity(), View.OnClickListener {

    private var mAuth: FirebaseAuth? = null

    private var mStatusView: TextView? = null
    private var mDetailView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firebase_ui)

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance()

        mStatusView = findViewById(R.id.status)
        mDetailView = findViewById(R.id.detail)

        findViewById<Button>(R.id.signInButton).setOnClickListener(this)
        findViewById<Button>(R.id.signOutButton).setOnClickListener(this)
    }

    override fun onStart() {
        super.onStart()
        updateUI(mAuth!!.currentUser)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            if (resultCode == Activity.RESULT_OK) {
                // Sign in succeeded

                updateUI(mAuth!!.currentUser)
            } else {
                // Sign in failed
                Toast.makeText(this, "Sign In Failed", Toast.LENGTH_SHORT).show()
                updateUI(null)
            }
        }
    }

    private fun startSignIn() {
        // Build FirebaseUI sign in intent. For documentation on this operation and all
        // possible customization see: https://github.com/firebase/firebaseui-android
        val intent = AuthUI.getInstance().createSignInIntentBuilder()
            .setIsSmartLockEnabled(!BuildConfig.DEBUG)
            .setAvailableProviders(listOf<AuthUI.IdpConfig>(
                AuthUI.IdpConfig.GoogleBuilder().build(),
                AuthUI.IdpConfig.EmailBuilder().build()))
            .setLogo(R.mipmap.ic_launcher)
            .build()

        startActivityForResult(intent, RC_SIGN_IN)
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            // Signed in
//
//            mStatusView!!.text = getString(R.string.firebaseui_status_fmt, user.email)
//            mDetailView!!.text = getString(R.string.id_fmt, user.uid)
//
//            this.findViewById<Button>(R.id.signInButton).setVisibility(View.GONE)
//            this.findViewById<Button>(R.id.signOutButton).setVisibility(View.VISIBLE)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()

        } else {
            // Signed out
            mStatusView!!.setText(R.string.signed_out)
            mDetailView!!.text = null

            findViewById<Button>(R.id.signInButton).setVisibility(View.VISIBLE)
            findViewById<Button>(R.id.signOutButton).setVisibility(View.GONE)
        }
    }

    private fun signOut() {
        AuthUI.getInstance().signOut(this)
        updateUI(null)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.signInButton -> startSignIn()
            R.id.signOutButton -> signOut()
        }
    }

    companion object {

        private val RC_SIGN_IN = 9001
    }
}
