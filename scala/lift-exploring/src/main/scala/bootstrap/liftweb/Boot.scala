package bootstrap.liftweb

import model.{ Auction, Customer, Supplier }
import net.liftweb.db.StandardDBVendor
import net.liftweb.http.{Html5Properties, LiftRules, Req}
import net.liftweb.mapper.Schemifier
import net.liftweb.sitemap.{Menu, SiteMap}
import net.liftweb.util.Props
import net.liftweb.mapper.{DB,DefaultConnectionIdentifier}
import net.liftweb.http.{LiftRules,S}


/**
 * A class that's instantiated early and run.  It allows the application
 * to modify lift's environment
 */
class Boot {
  def boot() = {
    // where to search snippet
    LiftRules.addToPackages("code.selector")

    // Build SiteMap
    /* def sitemap(): SiteMap = SiteMap(
     *   Menu.i("Home") / "index"
     * ) */ //::: Customer.menus


    val sitemap = List(
      Menu("Home") / "index",
      Menu("Search") / "search",
      Menu("History") / "history"
    ) ::: Customer.menus // http://localhost:8080/account/sign_up

    LiftRules.setSiteMap(SiteMap(sitemap:_*))

    // Use HTML5 for rendering
    LiftRules.htmlProperties.default.set((r: Req) =>
      new Html5Properties(r.userAgent))

    Schemifier.schemify(true, Schemifier.infoF _, Auction, //Bid, Customer,
           /* Order, OrderAuction, */ Supplier)


    DB.defineConnectionManager(DefaultConnectionIdentifier, DBVendor)
    LiftRules.unloadHooks.append(
      () => DBVendor.closeAllConnections_!())
    S.addAround(DB.buildLoanWrapper)
  }
}

object DBVendor extends StandardDBVendor(
  Props.get("db.class").openOr("org.h2.Driver"),
  Props.get("db.url").openOr("jdbc:h2:database/chapter_3"),
  Props.get("db.user"),
  Props.get("db.pass"))
