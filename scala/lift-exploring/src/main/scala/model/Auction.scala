package model

import net.liftweb.common.Full
import net.liftweb.mapper._

object Auction extends Auction with LongKeyedMetaMapper[Auction]{
  override def dbTableName = "auctions"
}
class Auction extends LongKeyedMapper[Auction]
    with IdPK
    with CreatedUpdated {
  def getSingleton = Auction

  // fields
  object name extends MappedString(this, 150)
  object description extends MappedText(this)
  object endsAt extends MappedDateTime(this)
  object outboundOn extends MappedDateTime(this)
  object inboundOn extends MappedDateTime(this)
  object flyingFrom extends MappedString(this, 100)
  object isClosed extends MappedBoolean(this){
    override def defaultValue = false
  }
  object startingAmount extends MappedDouble(this)

  // relationships
  object supplier extends LongMappedMapper(this, Supplier){
    override def dbColumnName = "supplier_id"
    override def validSelectValues =
      Full(Supplier.findMap(OrderBy(Supplier.name, Ascending)){
        case s: Supplier => Full(s.id.get -> s.name.get)
      })
  }
}


trait StorageFinal[F[_]] {

  def get(key: String): F[Option[String]]

  def put(key: String, data: String): F[Unit]

  def remove(key: String): F[Unit]

}

final class MapStorageFinal

extends StorageFinal[State[Map[String, String], ?]] {

  def get(key: String): State[Map[String, String], Option[String]] =

  State.inspect(_.get(key))

  def put(key: String, data: String): State[Map[String, String], Unit] =

  State.modify(_ + (key -> data))

  def remove(key: String): State[Map[String, String], Unit] =

  State.modify(_ - key)

}
