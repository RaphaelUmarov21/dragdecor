package dragdecor

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.*

import androidx.compose.material.Text
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.ClassLoaderResourceLoader
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@Composable
@ExperimentalFoundationApi
@Preview
fun PreviewTicTacToe() {
    val model = mutableStateOf(filledRoom())
    DragDecorView(model)
}

@ExperimentalFoundationApi
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DragDecorView(model: MutableState<DragDecor>) {
    //Colonne Principale------------------------------------------------------------------------------------------------------------
    Column(
        modifier = Modifier
            .border(1.dp, Color.Black)
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        //Refresh-------------------------------------------------------
        Box(
            modifier = Modifier
                .background(Color.White),
            contentAlignment = Alignment.TopStart
        )
        {
            Text(text = "Refresh", Modifier.clickable { model.value = model.value.refresh() })
        }
        Box(
            modifier = Modifier
                .background(Color.White),
            contentAlignment = Alignment.TopStart
        )
        {
            Text(text = "Supprimer dernier element", Modifier.clickable { model.value = model.value.suppFurniture() })
        }
        //---------------------------------------------------------------

        //Titre----------------------------------------------------------
        Text(
            text = "Drag&Decor",
            fontSize = 70.sp,
            textAlign = TextAlign.Center,
            color = Color.White
        )
        //---------------------------------------------------------------

        //Room & Color---------------------------------------------------
        Row(
            modifier = Modifier
                .height(8000.dp)
        ) {

            Column(
                modifier = Modifier
                    .border(1.dp, Color.Black)
                    .weight(2f)
                    .width(1800.dp)
                    .height(1600.dp)
                    .padding(5.dp)
                    .border(1.dp, Color.Black),
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                //Room---------------------------------
                Text(
                    text = "Room",
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center,
                    color = Color.White
                )

                Row(
                    modifier = Modifier
                        .border(1.dp, Color.Unspecified)
                        .weight(5f)
                        .width(800.dp)
                        .padding(5.dp)
                        .border(1.dp, Color.Black)
                        .background(model.value.room.getWallColor()),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Column {
                        val lamp = painterResource(
                            model.value.room.curFurnitures[0].getFichier(),
                            ClassLoaderResourceLoader()
                        )//parcours
                        DraggableImage(
                            image = lamp, size = 200
                        )
                    }
                    Box {
                        Column(
                            modifier = Modifier
                                .height(500.dp)
                                .fillMaxWidth()
                        ) {
                            val groupedFurnitures = model.value.room.curFurnitures.chunked(6)

                            // Créer une nouvelle colonne pour chaque groupe de meubles
                            groupedFurnitures.forEach { furnitureGroup ->
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Start
                                ) {
                                    furnitureGroup.forEach { furniture ->
                                        if (furniture.type != "lamp") {
                                            val image = painterResource(
                                                furniture.getFichier(),
                                                ClassLoaderResourceLoader()
                                            )
                                            DraggableImage(image = image, size = 100)
                                        }
                                    }
                                }
                            }
                        }
                        /*
                        repeat(model.value.curFurnitures.size) {
                        Text(
                            text = "Item"
                            /*,Modifier.draggable()*/
                        )
                    }
                    */

                    }
                    //----------------------------------------
                }
                    //Color-----------------------------------
                    Text(text = "")
                    Column(
                        modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Color : ",
                            fontSize = 30.sp,
                            textAlign = TextAlign.Center,
                            color = Color.White
                        )
                        Row(
                            modifier = Modifier
                                .padding(5.dp)
                        ) {

                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.padding(5.dp)
                            ) {
                                Text(
                                    text = "Wall :",
                                    color = Color.White
                                )
                                Row {
                                    Text(
                                        text = "White ",
                                        Modifier.clickable { model.value = model.value.changeWallColor(Color.White) },
                                        color = Color.White
                                    )
                                    Text(
                                        text = "Blue ",
                                        Modifier.clickable { model.value = model.value.changeWallColor(Color.Blue) },
                                        color = Color.Blue
                                    )
                                    Text(
                                        text = "Brown ",
                                        Modifier.clickable {
                                            model.value = model.value.changeWallColor(Color(155, 103, 50, 255))
                                        },
                                        color = Color(155, 103, 50, 255)
                                    )
                                }

                            }
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.padding(5.dp)
                            ) {
                                Text(
                                    text = "Lamp :",
                                    color = Color.White
                                )
                                Row {
                                    Text(
                                        text = "Yellow ",
                                        Modifier.clickable { model.value = model.value.changeLampColor(Color.Yellow) },
                                        color = Color.Yellow
                                    )
                                    Text(
                                        text = "White ",
                                        Modifier.clickable { model.value = model.value.changeLampColor(Color.White) },
                                        color = Color.White
                                    )
                                    Text(
                                        text = "Red ",
                                        Modifier.clickable { model.value = model.value.changeLampColor(Color.Red) },
                                        color = Color.Red
                                    )
                                }

                            }

                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.padding(5.dp)
                            ) {
                                Text(
                                    text = "Furnitures :",
                                    color = Color.White
                                )
                                Row {
                                    Text(
                                        text = "Black ",
                                        Modifier.clickable {
                                            model.value = model.value.changeFurnitureColor(Color.Black)
                                        },
                                        color = Color.Gray
                                    )
                                    Text(
                                        text = "White ",
                                        Modifier.clickable {
                                            model.value = model.value.changeFurnitureColor(Color.White)
                                        },
                                        color = Color.White
                                    )
                                    Text(
                                        text = "Brown ",
                                        Modifier.clickable {
                                            model.value = model.value.changeFurnitureColor(Color(155, 103, 50, 255))
                                        },
                                        color = Color(155, 103, 50, 255)
                                    )
                                }

                            }
                        }
                    }
                    //----------------------------------------

                //---------------------------------------------------------------
            }
                //Fourniture---------------------------------------------

                val scrollState = rememberScrollState()
                Column(
                    modifier = Modifier
                        .padding(20.dp)
                        .border(1.dp, Color.Black)
                        .clip(RoundedCornerShape(30.dp))
                        .weight(1f)
                        .height(600.dp)
                        .background(model.value.getListColor())


                        .verticalScroll(scrollState),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    //Fourniture pouvant etre mises

                    Text(
                        text = "Furniture",
                        fontSize = 30.sp,
                        color = model.value.getColor()
                    )
                    var i = 0
                    repeat(6) {

                        Row {
                            repeat(2) {
                                if (model.value.getFurniture(i).type != "lamp") {
                                    val furniture = model.value.getFurniture(i)
                                    /*-------------------------Ajouter le drag--------------------------------*/
                                    val image =
                                        painterResource(furniture.getFichier(), ClassLoaderResourceLoader())
                                    Image(
                                        modifier = Modifier
                                            .size(100.dp)
                                            .clickable { model.value = model.value.addFurniture(furniture) },
                                        painter = image, contentDescription = "logo"
                                    )

                                    /*-----------------------------------------------------------------*/

                                }
                                i++
                            }
                        }
                    }


                }


                //----------------------------------------------------
            }



//------------------------------------------------------------------------------------------------------
    }
}
    @Composable
    fun DraggableImage(image: Painter, size: Int) {
        // Utilisation de MutableState pour gérer la position de l'image
        var offset by remember { mutableStateOf(Offset(0f, 0f)) }

        Box(
            modifier = Modifier
                .offset { IntOffset(offset.x.roundToInt(), offset.y.roundToInt()) }
                .pointerInput(Unit) {
                    detectDragGestures { _, dragAmount ->
                        offset = Offset(offset.x + dragAmount.x, offset.y + dragAmount.y)
                    }
                }
                .wrapContentSize()
        ) {
            Image(
                painter = image,
                contentDescription = "Logo",
                modifier = Modifier.size(size.dp)

            )
        }
    }

