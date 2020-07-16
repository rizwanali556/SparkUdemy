import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by Rizwan on 7/16/2020.
  */
object RatingsCounter {
  def main(args: Array[String]) {

    Logger.getLogger("org").setLevel(Level.ERROR)

    val conf = new SparkConf().setAppName("RatingsCounter").setMaster(args(0))
    val sc = new SparkContext(conf)

    val lines = sc.textFile(args(1)) //**assets/ml-100k/u.data*/

    val ratings = lines.map(x => x.toString().split("\t")(2))

    val results = ratings.countByValue()

    val sortedResults = results.toSeq.sortBy(_._1)

    sortedResults.foreach(println)
  }


}
