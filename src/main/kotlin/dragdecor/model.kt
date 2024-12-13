package dragdecor

import androidx.compose.ui.graphics.Color

class Furniture(
    val type: String, val color: String, var file: String  //a changer;

) {
    fun changeFile(newFile: String) {
        file = newFile
    }

    fun changeColor(newColor: String): Furniture {
        return Furniture(type = this.type, color = newColor, file = this.file)
    }

    fun getFichier(): String {
        return "ressource/$color/$file"
    }
}


class Room(
    private val wallColor: Color = Color.White,
    private val lampColor: Color = Color.Yellow,
    private val furColor: Color = Color.Black,
    val curFurnitures: List<Furniture> = List(1) { Furniture(type = "lamp", color = "yellow", file = "lamp.png") }
) {
    fun getWallColor(): Color {
        return wallColor
    }

    fun getFurColor(): Color {
        return furColor
    }

    fun addFurniture(fur: Furniture): Room {
        val new = curFurnitures.toMutableList()
        new.add(fur)
        return Room(
            wallColor = this.wallColor, lampColor = this.lampColor, furColor = this.furColor, curFurnitures = new
        )
    }

    fun suppFurniture(): Room {
        if (curFurnitures.size!=1) {
            val new = curFurnitures.toMutableList()
            new.removeAt(new.size-1)
            return Room(
                wallColor = this.wallColor, lampColor = this.lampColor, furColor = this.furColor, curFurnitures = new
            )
        }
        return this
    }

    fun changeWallColor(color: Color): Room {
        return Room(
            wallColor = color, lampColor = this.lampColor, furColor = this.furColor, curFurnitures = this.curFurnitures
        )
    }

    fun changeLampColor(color: Color): Room {
        val new = curFurnitures.toMutableList()
        if (color == Color.White) {
            new[0] = curFurnitures[0].changeColor("white")
        }
        if (color == Color.Yellow) {
            new[0] = curFurnitures[0].changeColor("yellow")
        }
        if (color == Color.Red) {
            new[0] = curFurnitures[0].changeColor("red")
        }
        return Room(
            wallColor = this.wallColor, lampColor = color, furColor = this.furColor, curFurnitures = new
        )
    }

    fun changeFurnitureColor(color: Color): Room {
        val new = curFurnitures.toMutableList()
        for (i in curFurnitures.indices) {
            if (curFurnitures[i].type != "lamp") {
                if (color == Color.Black) {
                    new[i] = curFurnitures[i].changeColor("black")
                }
                if (color == Color.White) {
                    new[i] = curFurnitures[i].changeColor("white")
                }
                if (color == Color(155, 103, 50, 255)) {
                    new[i] = curFurnitures[i].changeColor("brown")
                }
            }
        }

        return Room(
            wallColor = this.wallColor, lampColor = this.lampColor, furColor = color, curFurnitures = new
        )
    }

}

//class contenant la liste de toutes les fournitures et la chambre
data class DragDecor(
    private val listColor: Color = Color.White,
    private val furSColor: String = "black",
    private val lampSColor: String = "white",
    private val furnitures: List<Furniture> = listOf(
        Furniture(type = "fur", color = "black", file = "books.png"),
        Furniture(type = "fur", color = "black", file = "cat.png"),
        Furniture(type = "fur", color = "black", file = "chair1.png"),
        Furniture(type = "fur", color = "black", file = "chair2.png"),
        Furniture(type = "fur", color = "black", file = "chair3.png"),
        Furniture(type = "fur", color = "black", file = "chandelier.png"),
        Furniture(type = "fur", color = "black", file = "desk.png"),
        Furniture(type = "lamp", color = "yellow", file = "lamp.png"),///////
        Furniture(type = "fur", color = "black", file = "picture.png"),
        Furniture(type = "fur", color = "black", file = "plant.png"),
        Furniture(type = "fur", color = "black", file = "table.png"),
        Furniture(type = "fur", color = "black", file = "window.png")
    ), val room: Room = Room()
) {
    fun getFurniture(id: Int): Furniture {
        return furnitures[id]
    }
    fun getListColor(): Color {
        if (room.getWallColor()==Color.White && furSColor=="white"){
            return Color.Black;
        }
        return room.getWallColor()
    }

    fun getColor(): Color {
        return room.getFurColor();
    }

    fun refresh(): DragDecor {
        return DragDecor(
            listColor = Color.White,
            furSColor = "black",
            lampSColor = "white",
            furnitures = listOf(
                Furniture(type = "fur", color = "black", file = "books.png"),
                Furniture(type = "fur", color = "black", file = "cat.png"),
                Furniture(type = "fur", color = "black", file = "chair1.png"),
                Furniture(type = "fur", color = "black", file = "chair2.png"),
                Furniture(type = "fur", color = "black", file = "chair3.png"),
                Furniture(type = "fur", color = "black", file = "chandelier.png"),
                Furniture(type = "fur", color = "black", file = "desk.png"),
                Furniture(type = "lamp", color = "yellow", file = "lamp.png"),///////
                Furniture(type = "fur", color = "black", file = "picture.png"),
                Furniture(type = "fur", color = "black", file = "plant.png"),
                Furniture(type = "fur", color = "black", file = "table.png"),
                Furniture(type = "fur", color = "black", file = "window.png")
            ), room = Room()
        )
    }

    fun suppFurniture(): DragDecor {
        return copy(room = room.suppFurniture())
    }

    fun addFurniture(fur: Furniture): DragDecor {
        return copy(room = room.addFurniture(fur))
    }

    fun changeWallColor(color: Color): DragDecor {
        return copy(room = room.changeWallColor(color))
    }

    fun changeLampColor(color: Color): DragDecor {
        if (color == Color.Yellow) {

            return copy(
                listColor = listColor,
                lampSColor = "yellow",
                furSColor = this.furSColor,
                furnitures = listOf(
                    Furniture(type = "fur", color = furSColor, file = "books.png"),
                    Furniture(type = "fur", color = furSColor, file = "cat.png"),
                    Furniture(type = "fur", color = furSColor, file = "chair1.png"),
                    Furniture(type = "fur", color = furSColor, file = "chair2.png"),
                    Furniture(type = "fur", color = furSColor, file = "chair3.png"),
                    Furniture(type = "fur", color = furSColor, file = "chandelier.png"),
                    Furniture(type = "fur", color = furSColor, file = "desk.png"),
                    Furniture(type = "lamp", color = "yellow", file = "lamp.png"),///////
                    Furniture(type = "fur", color = furSColor, file = "picture.png"),
                    Furniture(type = "fur", color = furSColor, file = "plant.png"),
                    Furniture(type = "fur", color = furSColor, file = "table.png"),
                    Furniture(type = "fur", color = furSColor, file = "window.png")
                ),
                room = room.changeLampColor(color)
            )
        }

        if (color == Color.Red) {
            return copy(
                listColor = listColor,
                lampSColor = "red",
                furSColor = this.furSColor,
                furnitures = listOf(
                    Furniture(type = "fur", color = furSColor, file = "books.png"),
                    Furniture(type = "fur", color = furSColor, file = "cat.png"),
                    Furniture(type = "fur", color = furSColor, file = "chair1.png"),
                    Furniture(type = "fur", color = furSColor, file = "chair2.png"),
                    Furniture(type = "fur", color = furSColor, file = "chair3.png"),
                    Furniture(type = "fur", color = furSColor, file = "chandelier.png"),
                    Furniture(type = "fur", color = furSColor, file = "desk.png"),
                    Furniture(type = "lamp", color = "red", file = "lamp.png"),///////
                    Furniture(type = "fur", color = furSColor, file = "picture.png"),
                    Furniture(type = "fur", color = furSColor, file = "plant.png"),
                    Furniture(type = "fur", color = furSColor, file = "table.png"),
                    Furniture(type = "fur", color = furSColor, file = "window.png")
                ),
                room = room.changeLampColor(color)
            )
        }
        return copy(
            listColor = listColor,
            lampSColor = "white",
            furSColor = this.furSColor,
            furnitures = listOf(
                Furniture(type = "fur", color = furSColor, file = "books.png"),
                Furniture(type = "fur", color = furSColor, file = "cat.png"),
                Furniture(type = "fur", color = furSColor, file = "chair1.png"),
                Furniture(type = "fur", color = furSColor, file = "chair2.png"),
                Furniture(type = "fur", color = furSColor, file = "chair3.png"),
                Furniture(type = "fur", color = furSColor, file = "chandelier.png"),
                Furniture(type = "fur", color = furSColor, file = "desk.png"),
                Furniture(type = "lamp", color = "white", file = "lamp.png"),///////
                Furniture(type = "fur", color = furSColor, file = "picture.png"),
                Furniture(type = "fur", color = furSColor, file = "plant.png"),
                Furniture(type = "fur", color = furSColor, file = "table.png"),
                Furniture(type = "fur", color = furSColor, file = "window.png")
            ),
            room = room.changeLampColor(color)
        )
    }

    fun changeFurnitureColor(color: Color): DragDecor {

        if (color == Color.White) {
            return copy(
                listColor = Color.Black,
                furSColor = "white",
                lampSColor = this.lampSColor,
                furnitures = listOf(
                    Furniture(type = "fur", color = "white", file = "books.png"),
                    Furniture(type = "fur", color = "white", file = "cat.png"),
                    Furniture(type = "fur", color = "white", file = "chair1.png"),
                    Furniture(type = "fur", color = "white", file = "chair2.png"),
                    Furniture(type = "fur", color = "white", file = "chair3.png"),
                    Furniture(type = "fur", color = "white", file = "chandelier.png"),
                    Furniture(type = "fur", color = "white", file = "desk.png"),
                    Furniture(type = "lamp", color = lampSColor, file = "lamp.png"),///////
                    Furniture(type = "fur", color = "white", file = "picture.png"),
                    Furniture(type = "fur", color = "white", file = "plant.png"),
                    Furniture(type = "fur", color = "white", file = "table.png"),
                    Furniture(type = "fur", color = "white", file = "window.png")
                ),
                room = room.changeFurnitureColor(color)
            )
        }

        if (color == Color(155, 103, 50, 255)) {
            return copy(
                listColor = Color.White,
                furSColor = "brown",
                lampSColor = this.lampSColor,
                furnitures = listOf(
                    Furniture(type = "fur", color = "brown", file = "books.png"),
                    Furniture(type = "fur", color = "brown", file = "cat.png"),
                    Furniture(type = "fur", color = "brown", file = "chair1.png"),
                    Furniture(type = "fur", color = "brown", file = "chair2.png"),
                    Furniture(type = "fur", color = "brown", file = "chair3.png"),
                    Furniture(type = "fur", color = "brown", file = "chandelier.png"),
                    Furniture(type = "fur", color = "brown", file = "desk.png"),
                    Furniture(type = "lamp", color = lampSColor, file = "lamp.png"),///////
                    Furniture(type = "fur", color = "brown", file = "picture.png"),
                    Furniture(type = "fur", color = "brown", file = "plant.png"),
                    Furniture(type = "fur", color = "brown", file = "table.png"),
                    Furniture(type = "fur", color = "brown", file = "window.png")
                ),
                room = room.changeFurnitureColor(color)
            )
        }
        return copy(
            listColor = Color.White,
            furSColor = "black",
            lampSColor = this.lampSColor,
            furnitures = listOf(
                Furniture(type = "fur", color = "black", file = "books.png"),
                Furniture(type = "fur", color = "black", file = "cat.png"),
                Furniture(type = "fur", color = "black", file = "chair1.png"),
                Furniture(type = "fur", color = "black", file = "chair2.png"),
                Furniture(type = "fur", color = "black", file = "chair3.png"),
                Furniture(type = "fur", color = "black", file = "chandelier.png"),
                Furniture(type = "fur", color = "black", file = "desk.png"),
                Furniture(type = "lamp", color = lampSColor, file = "lamp.png"),///////
                Furniture(type = "fur", color = "black", file = "picture.png"),
                Furniture(type = "fur", color = "black", file = "plant.png"),
                Furniture(type = "fur", color = "black", file = "table.png"),
                Furniture(type = "fur", color = "black", file = "window.png")
            ),
            room = room.changeFurnitureColor(color)
        )
    }



}

//Test
fun filledRoom(): DragDecor {
    return DragDecor()
}