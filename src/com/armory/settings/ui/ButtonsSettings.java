/*
 * Copyright (C) 2018 Invictrix ROM
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.armory.settings.ui;

import android.os.Bundle;
import android.os.UserHandle;
import android.provider.Settings;

import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.Preference.OnPreferenceChangeListener;

import com.android.settings.R;

public class ButtonsSettings extends InvictrixSettingsFragment implements OnPreferenceChangeListener {

    private static final String VOLUME_KEY_CURSOR_CONTROL = "volume_key_cursor_control";

    private ListPreference mCursorControl;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title = getResources().getString(R.string.buttons_settings_title);
        addPreferencesFromResource(R.xml.settings_buttons);

        int cursorVal = Settings.System.getIntForUser(getContentResolver(),
                VOLUME_KEY_CURSOR_CONTROL, 0, UserHandle.USER_CURRENT);
        mCursorControl = (ListPreference) findPreference(VOLUME_KEY_CURSOR_CONTROL);
        mCursorControl.setValue(String.valueOf(cursorVal));
        mCursorControl.setOnPreferenceChangeListener(this);

    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        if (preference == mCursorControl) {
            int val = (Integer) newValue;
            Settings.System.putIntForUser(getContentResolver(),
                    Settings.System.VOLUME_KEY_CURSOR_CONTROL, val, UserHandle.USER_CURRENT);
            return true;
        }
        return false;
    }

}
