/**
  * Created by Shivangi on 23/05/16.
  */
import scala.collection.mutable.ListBuffer

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    balance("hello there".toList)
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }
  /**
    * Exercise 1
    */
  def pascal(c: Int, r: Int): Int = {
    if(c==r || c==0) 1
    else pascal(c-1,r-1)+pascal(c,r-1)
  }

  /**
    * Exercise 2
    */
  def balance(chars: List[Char]): Boolean = {
    var sum =0
    checkparen(chars.to[ListBuffer],sum)
  }

  def checkparen(chars:ListBuffer[Char], sum:Int):Boolean={
    var localsum = sum
    if((chars.isEmpty && localsum != 0) || (localsum <0)) false
    else if(chars.isEmpty && localsum == 0) true
    else{
      if(chars.head == '(') localsum += 1
      else if (chars.head == ')') localsum -= 1
      chars.remove(0)
      checkparen(chars,localsum)
    }
  }
  /**
    * Exercise 3
    */
  def countChange(money: Int, coins: List[Int]): Int = {
    if(money ==0) 1
    else if(coins.isEmpty || money < 0)0
    else countChange(money,coins.tail)+countChange(money-coins.head,coins)
  }
}
