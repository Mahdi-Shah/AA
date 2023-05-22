package com.example.demo.view;

import com.example.demo.model.DataBase;

public enum Labels {
    SIGN_UP("Sign Up", "ثبت نام"),
    GO_TO_LOGIN_MENU("Go to login menu", "به منوی ورود برو"),
    SKIP("Skip", "رد شدن"),
    EXIT("Exit", "خروج"),
    REGISTER_MENU("Register Menu", "منوی ثبت نام"),
    AVATAR("Avatar", "آواتار"),
    NAME("Name", "نام"),
    ENTER_YOUR_NAME("Enter your name", "اسم خود را وارد کنید"),
    PASSWORD("Password", "رمز"),
    ENTER_YOUR_PASSWORD("Enter your password", "رمز خود را وارد کنید"),
    TWOSOME_GAME("Twosome Game", "بازی دونفره"),
    ENTER_RIVAL_NAME("Enter your rival name", "نام حریف خود را وارد کنید"),
    PLAY_GAME("Play Game", "شروع بازی"),
    START_GAME("Start Game", "شروع بازی"),
    MUTE("Mute", "قطع کردن صدا"),
    BLACK_WHITE("Black & White", "سیاه و سفید"),
    SUBMIT("Submit", "ثبت"),
    BACK("Back", "بازگشت"),
    BALL_COUNT("Ball Count", "تعداد توپ"),
    ROTATION_SPEED("Rotation Speed", "سرعت چرخش"),
    WIND_SPEED("Wind Speed", "سرعت باد"),
    FREEZE_TIME("Freeze Time", "زمان حالت یخ زده"),
    SETTINGS("Settings", "تنظیمات"),
    STOP("Stop", "توقف"),
    RIGHT_FIRST("Right (1st player)", "جرکت به راست بازیکن اول"),
    LEFT_FIRST("Left (1st player)", "حرکت به چپ بازیکن اول"),
    RIGHT_SECOND("Right (2th player)", "حرکت به راست بازیکن دوم"),
    LEFT_SECOND("Left (2th player)", "حرکت به چپ بازیکن دوم"),
    SHOOT_FIRST("Shoot (1st player)", "پرتاب بازیکن اول"),
    SHOOT_SECOND("Shoot (2th player)", "پرتاب بازیکن دوم"),
    FREEZE("Freeze", "فریز"),
    SCORES("Scores", "امتیازات"),
    TIME("time", "زمان"),
    FIRST_PLAYER_SCORE("First Player Score", "امتیاز بازیکن اول"),
    SECOND_PLAYER_SCORE("Second Player Score", "امتیاز بازیکن دوم"),
    PLAYER_SCORE("Player Score", "امتیاز بازیکن"),
    USERNAME("Username", "نام کاربری"),
    SCORE("Score", "امتیاز"),
    EASY("easy", "آسان"),
    MIDDLE("middle", "متوسط"),
    HARD("hard", "سخت"),
    PROFILE_MENU("Profile Menu", "منوی پروفایل"),
    CHANGE_USERNAME("Change Username", "تعویض نام کاربری"),
    CHANGE_PASSWORD("Change Password", "تعویض رمز"),
    LOGOUT("Logout", "خروج از حساب کاربری"),
    DELETE_ACCOUNT("Delete Account", "حذف حساب کاربری"),
    RESUME("Resume", "ادامه"),
    RESTART("Restart", "شروع مجدد"),
    SAVE_GAME("Save Game", "ذخیره بازی"),
    MAIN_MENU("Main Menu", "منوی اصلی"),
    NEW_GAME("New Game", "بازی جدید"),
    CONTINUE_GAME("Continue Game", "ادامه بازی"),
    SCOREBOARD("Scoreboard", "جدول امتیازات"),
    LOGIN_MENU("Login Menu", "منوی ورود"),
    LOGIN("Login", "ورود"),
    UPLOAD_AVATAR("Upload\nAvatar", "بارگذاری\nآواتار"),
    RANDOM_AVATAR("Random\nAvatar", "آواتار\nتصادفی"),




    YOUR_RIVAL_NAME_IS_INCORRECT("Your rival name is incorrect", "نام کاربری حریف شما اشتباه است"),
    NUMBER_OF_BALLS_CANT_BE_ZERO("number of balls can't be zero", "تعداد توپ ها نمی تواند صفر باشد"),
    ROTATION_SPEED_CANT_BE_ZERO("Rotation speed can't be zero", "سرعت چرخش نمی تواند صفر باشد"),
    CHANGES_SAVED_SUCCESSFULLY("Changes saved successfully", "تغییرات با موفقیت ذخیره شد"),
    KEY_CHANGED_SUCCESSFULLY("key changed successfully", "کلید با موفقیت ذخیره شد"),
    YOUR_USERNAME_IS_REPETITIVE("your username is repetitive", "این نام کاربری تکراری است"),
    PLEASE_ENTER_YOUR_NAME("please enter your name", "لطفا نام خود را وارد کنید"),
    PLEASE_ENTER_YOUR_PASSWORD("please enter your password", "لطفا رمز عبور خود را وارد منید"),
    please_choose_your_avatar("please choose your avatar", "لطفا آواتار خود را انتخاب کنید"),
    ENTER_CORRECT_NAME("Enter correct name", "نام کاربری درست وارد کنید"),
    PASSWORD_FIELD_CANT_BE_EMPTY("password field can't be empty", "رمز عبور نمی تواند خالی باشد"),
    YOUR_PASSWORD_IS_INVALID("Your password is invalid", "رمز عبور شما نادرست است"),
    USERNAME_FIELD_CANT_BE_EMPTY("Username field cant be empty", "نام کاربری نمی تواند خالی باشد"),
    YOUR_USERNAME_OR_PASSWORD_IS_WRONG("your username or password is wrong", "رمز عبور یا نام کابری شما نادرست است"),
    YOU_HAVE_NOT_ANOTHER_BALL("You haven't another ball", "توپ دیگری ندارید"),





    MAGIC_FORCE_DEGREE("Magic Force Degree", "درجه ی نیروی جادویی"),
    FIRST_PLAYER_BALLS_REMAIN("First Player\nballs remain", "توپ های باقی مانده\nبازیکن اول"),
    SECOND_PLAYER_BALLS_REMAIN("Second Player\nballs remain", "توپ های باقی مانده\nبازیکن دوم"),

    ;

    private String english;
    private String persian;

    Labels(String english, String persian) {
        this.english = english;
        this.persian = persian;
    }

    static String getLabel(Labels label) {
        for (Labels label1 : Labels.values()) {
            if (label1.equals(label)) {
                if (DataBase.getLanguage().equals("english"))
                    return label.english;
                else
                    return label.persian;
            }
        }
        return "has no name";
    }
}
