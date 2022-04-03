package org.techtown.soptseminar


open class Human(val name : String = "이준원"){
    var age = 10
    constructor(name : String , age : Int) : this(name){
        this.age = age
    }

    open fun introduce(){
        println("안녕 내 이름은 ${name} 이야")
        println("난 ${age}살이야")
    }
}
class Ningen(val part : String = "안드로이드") : Human(){ // 상속을 받으려면 Human(조상)클래스의 접근제어자가 open이여야 한다.

    override fun introduce() {
        super.introduce()
        println("${name}는 SOPT ${part}파트야")
    }

}
fun main() {
    val h = Human("이서원", 30)
    h.introduce()
    println()

    val n = Ningen()
    n.introduce()
}

