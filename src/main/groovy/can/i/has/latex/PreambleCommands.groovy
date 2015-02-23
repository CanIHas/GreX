package can.i.has.latex

import can.i.has.latex.model.Command
import can.i.has.latex.model.StringRenderable

import java.text.DateFormat
import java.text.SimpleDateFormat


class PreambleCommands {
    static Command usePackage(String pkgName, String... options){
        new Command("usepackage", options.toList(), [ new StringRenderable(pkgName) ])
    }

    static Command setTitle(String title){
        new Command("title", title)
    }

    static Command setAuthor(String author){
        new Command("author", author)
    }

    static Command setDate(){
        new Command("date", " ")
    }

    static Command setDate(String date){
        new Command("date", date)
    }

    static DateFormat defaultDateFormat = new SimpleDateFormat()

    static Command setDate(Date date, DateFormat dateFormat=null){
        if (dateFormat==null)
            dateFormat = defaultDateFormat
        setDate(dateFormat.format(date))
    }

    static Command setCurrentDate(DateFormat dateFormat=null){
        setDate(new Date(), dateFormat)
    }
}
