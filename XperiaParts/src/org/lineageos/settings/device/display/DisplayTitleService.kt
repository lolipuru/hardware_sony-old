/*
 * Copyright (C) 2022 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package org.lineageos.settings.device.display

import android.service.quicksettings.Tile
import android.service.quicksettings.TileService

class DisplayTileService : TileService() {
    private val creatorModeUtils: CreatorModeUtils = CreatorModeUtils(this)

    override fun onStartListening() {
        super.onStartListening()

        val state = creatorModeUtils.isEnabled

        if (state)
            qsTile.state = Tile.STATE_INACTIVE
        else
            qsTile.state = Tile.STATE_ACTIVE

        qsTile.updateTile()
    } 

    override fun onClick() {
        super.onClick()

        if (qsTile.state == Tile.STATE_ACTIVE) {
            // enable --> disable
            qsTile.state = Tile.STATE_INACTIVE
        } else {
            // disable -> enable
            qsTile.state = Tile.STATE_ACTIVE
        }

        qsTile.updateTile()
    }
}