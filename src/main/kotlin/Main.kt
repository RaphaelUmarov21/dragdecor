import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import dragdecor.DragDecorView
import dragdecor.DragDecor

@ExperimentalFoundationApi
fun main() = application {
    Window(
        title= "Drag&Decor",
        onCloseRequest = ::exitApplication) {
        val model = remember { mutableStateOf(DragDecor()) }
        DragDecorView(model);

    }
}
