package com.nohjason.momori.ui.root.key

import androidx.compose.ui.tooling.data.Group

sealed class NavGroup(val group: String){
    sealed class Main(val id: String, val title: String) : NavGroup("main"){
        object MainMap: Main("main", "지도스크린")
        object On_Board_1: Main("on_board_1", "온보딩1")
        object On_Board_2: Main("on_board_2", "온보딩2")
        object On_Board_3: Main("on_board_3", "온보딩3")
    }
    sealed class Peed(val id: String, val title: String) : NavGroup("peed"){
        object Watch_Peed: Peed("view", "피드보기")
        object Upload_Peed: Peed("upload", "피드제작")
    }
    sealed class Setting(val id: String, val title: String) : NavGroup("setting"){
        object Setting_View: Setting("setting", "설정창")
        object Setting_Profile_View: Setting("profile_setting", "프로필 설정창")
    }
}

//enum class Key{
//    MainScreen,
//    OnBoardScreen,
//    ProFileScreen,
//    SettingScreen,
//    UpLoadScreen,
//    PostScreen
//}


//sealed class NavGroup(val group: String) {
//    sealed class Auth(val id: String, val title: String) : NavGroup("auth") {
//        object ON_BOARD: Auth("on_board", "온보딩")
//        object LOGIN: Auth("login", "로그인")
//    }
//
//    sealed class Main(val id: String, val title: String, val icon: Int) : NavGroup("main") {
//        object MAIN: Main("main", "메인", -1)
//        object HOME: Main("home", "홈", R.drawable.ic_home)
//        object QUIZ: Main("quiz", "퀴즈", R.drawable.ic_quiz)
//        object MY: Main("my", "MY", R.drawable.ic_my)
//    }
//
//    sealed class Feature(val id: String, val title: String) : NavGroup("feature") {
//        object BOARD: Feature("board", "게시글")
//        object POST: Feature("post", "글쓰기")
//    }
//}