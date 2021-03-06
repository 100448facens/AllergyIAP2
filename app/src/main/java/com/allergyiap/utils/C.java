package com.allergyiap.utils;

/**
 * Created by Globalia-5 on 16/09/2016.
 */

/**
 * Application Constants
 */
public final class C {

    /**
     * App Code Name
     * Unique name which app
     */
    public static final String appCodeName = "allergyiap-android";

    /**
     * App Package Name
     */
    private static final String appPkg = C.class.getPackage().getName();

    /**
     * Common Prefs
     */
    public static final class Prefs {
        // Language (SYNC with preferences.xml)
        public static final String LANGUAGE = "prefs.language";

        public static final String USER_ALLERGIES = "prefs.user.allergies";

        public static final String NOTIFICATIONS                  = "prefs.notification.list";
        public static final String NOTIFICATIONS_ENABLED          = "prefs.notification.enabled";
        public static final String NOTIFICATIONS_SOUND            = "prefs.notification.sound";
        public static final String NOTIFICATIONS_VIBRATION        = "prefs.notification.vibration";
        public static final String NOTIFICATIONS_WEEK             = "prefs.notification.days_week";
        public static final String NOTIFICATIONS_HOUR             = "time_alarm";

        // Login Fields
        public static final String LOGIN_NAME  = "prefs.login.name";
        public static final String LOGIN_PASS  = "prefs.login.pass";
        public static final String LOGIN_TOKEN = "prefs.login.token";


    }

    /**
     * Language
     */
    public static final class Lang {
        public static final String LANG_EN = "en";
        public static final String LANG_ES = "es";
        public static final String LANG_CA = "ca";
        public static final String AVAILABLE = "en,es,ca";
    }

    /**
     * Network
     */
    public static final class Network {
        // HOST
        public static final String HOST = "http://alumnes-grp02.udl.cat";
        //public static final String HOST = "http://10.0.2.2:8080";
        // WebService URL
        public static final String WS_URL = HOST + "/AllergyIAPWS/rest/allergyws/";

        /**
         * WebService
         */
        public static final class WS {
            /**
             * Commons WS
             */
            public static final class Common {
                public static final String UPDATE_DEVICE_TOKEN = WS_URL + "action=updateDeviceToken";
                public static final String GET_COMPANIES_AND_CHATS = WS_URL + "action=getCompaniesAndChats";
            }

        }
    }

    /**
     * Google Play Service
     */
    public static final class GooglePlay {
        public final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
    }

    /**
     * Google Cloud Messaging
     */
    public static final class GCM {
        // Google Cloud Project Number
        public static final String SENDER_ID = "812285024260";
        // Bundle extras
        //public static final String EXTRA_MESSAGE 		= "message";
        // Shared Prefs
        public static final String PROPERTY_REG_ID = "gcm.registration_id";
        public static final String PROPERTY_APP_VERSION = "gcm.app_version";
        /**
         * Notifications ID
         * <p>
         * Notification identifiers unique within the application.<br>
         * If a notification with the same id has already been posted, it will be replaced.
         */
        public static final int NOTIFICATION_ID = 1;
    }

    /**
     * Notification
     */
    public static final class Notification {
        public static final class ExtraKey {
            public static final String MESSAGE = "message";
        }

        public static final class Result {
            public static final String VAR_NOTICE = "NOTICIA";
        }
    }

    /**
     * Intents Extra Data
     */
    public static final class IntentExtra {

        /**
         * Inents Sender
         */
        public static final class Sender {
            // Extra name
            public static final String EXTRA_NAME = appPkg + ".intent_sender";

            public static final String VAR_ALLERGY = appPkg + ".allergy_id";
            public static final String VAR_ALLERGY2 = appPkg + ".allergy_object";
            public static final String VAR_PRODUCT = appPkg + ".product_object";
            public static final String VAR_STATION = appPkg + ".product_station";
        }
    }

    public static final class Img {
        public static final int NOTICE_WIDTH = 300;
        public static final int NOTICE_HEIGHT = 300;
    }
}
