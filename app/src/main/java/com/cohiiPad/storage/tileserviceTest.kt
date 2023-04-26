package com.cohiiPad.storage
//import android.content.ComponentName
//import android.graphics.drawable.Icon
//import android.service.quicksettings.Tile
//import android.service.quicksettings.TileService
//import android.view.View
//import androidx.appcompat.widget.SwitchCompat
//
//// Define a constant for your tile ID
//const val TILE_ID = "com.example.myapp.MY_TILE"
//
//// Define a variable for your tile service
//var tileService: TileService? = null
//
//// Define a function to update the tile state
//fun updateTileState(state: Boolean) {
//    // Get the tile instance from the tile service
//    val tile = tileService?.qsTile
//    // Set the state of the tile based on the parameter
//    if (state) {
//        // Set the state to active and change the icon accordingly
//        tile?.state = Tile.STATE_ACTIVE
//        tile?.icon = Icon.createWithResource(this, R.drawable.ic_tile_on)
//    } else {
//        // Set the state to inactive and change the icon accordingly
//        tile?.state = Tile.STATE_INACTIVE
//        tile?.icon = Icon.createWithResource(this, R.drawable.ic_tile_off)
//    }
//    // Update the tile UI
//    tile?.updateTile()
//}
//
//// Define a function to handle click events on your switch button in your app
//fun onSwitchClicked(view: View) {
//    // Get the switch button instance from the view parameter
//    val switchButton = view as SwitchCompat
//    // Get the current state of the switch button
//    val switchState = switchButton.isChecked
//    // Update the tile state with the same value as the switch state
//    updateTileState(switchState)
//}
//
//// Define a class for your custom tile service that extends TileService
//class MyTileService : TileService() {
//
//    override fun onCreate() {
//        super.onCreate()
//        // Assign this service instance to your global variable
//        tileService = this
//        // Request listening state for your tile ID
//        requestListeningState(this, ComponentName(this, MyTileService::class.java))
//
//    }
//
//    override fun onTileAdded() {
//        super.onTileAdded()
//        // Update the initial state of your tile when it is added to the notification bar
//        updateTileState(false)
//
//    }
//
//}