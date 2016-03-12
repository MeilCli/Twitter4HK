package com.twitter.meil_mitu.twitter4hk.util

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.URLSpan
import com.twitter.meil_mitu.twitter4hk.data.*
import java.util.*

object EntityUtils {

    private val color = Color.parseColor("#DE1E88E5")

    fun toLinkHtml(text: String, entities: Entities): SpannableStringBuilder {
        return toLinkHtml(text, makeEntity(entities))
    }

    internal fun toLinkHtml(text: String, entities: Array<Entity>): SpannableStringBuilder {
        val sb = SpannableStringBuilder()
        var currentEntityIndex = 0
        var index = 0
        var semicolonIndex: Int
        val size = text.length
        var spanStart: Int
        var escapedText: String
        var spanText: String
        var escapeChar: Char?
        while (index < size) {
            if (currentEntityIndex < entities.size) {
                if (entities[currentEntityIndex].start == index) {
                    spanStart = sb.length
                    if (entities[currentEntityIndex].type == EntityType.URL) {
                        spanText = entities[currentEntityIndex].to
                    } else {
                        spanText = text[index] + entities[currentEntityIndex].to
                    }
                    sb.append(spanText)
                    sb.setSpan(URLSpan(spanText), spanStart, sb.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                    sb.setSpan(ForegroundColorSpan(color), spanStart, sb.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                    index += entities[currentEntityIndex].end - entities[currentEntityIndex].start
                    currentEntityIndex++
                    continue
                }
            }
            if (text[index] == '&') {
                semicolonIndex = text.indexOf(';', index)
                if (semicolonIndex != -1) {
                    escapedText = text.substring(index, semicolonIndex + 1)
                    escapeChar = escapeMap[escapedText]
                    if (escapeChar != null) {
                        sb.append(escapeChar)
                        index += escapedText.length
                        continue
                    }
                }
            }
            sb.append(text[index])
            index++
        }
        return sb
    }

    fun toLinkURL(text: String, entities: Entities): String {
        return toLinkURL(text, makeEntity2(entities))
    }

    internal fun toLinkURL(text: String, entities: Array<Entity>): String {
        val sb = StringBuilder()
        var currentEntityIndex = 0
        var index = 0
        var semicolonIndex: Int
        val size = text.length
        var escapedText: String
        var escapeChar: Char?
        while (index < size) {
            if (currentEntityIndex < entities.size) {
                if (entities[currentEntityIndex].start == index) {
                    if (entities[currentEntityIndex].type != EntityType.URL) {
                        currentEntityIndex++
                        continue
                    }
                    sb.append(entities[currentEntityIndex].to)
                    index += entities[currentEntityIndex].end - entities[currentEntityIndex].start
                    currentEntityIndex++
                    continue
                }
            }
            if (text[index] == '&') {
                semicolonIndex = text.indexOf(';', index)
                if (semicolonIndex != -1) {
                    escapedText = text.substring(index, semicolonIndex + 1)
                    escapeChar = escapeMap[escapedText]
                    if (escapeChar != null) {
                        sb.append(escapeChar)
                        index += escapedText.length
                        continue
                    }
                }
            }
            sb.append(text[index])
            index++
        }
        return sb.toString()
    }

    private fun makeEntity(data: Entities): Array<Entity> {
        val list = ArrayList<Entity>()
        setEntity(list, data.URL)
        setEntity(list, data.Media)
        setEntity(list, data.UserMention)
        setEntity(list, data.Hashtag)
        setEntity(list, data.Symbol)
        Collections.sort(list, comparator)
        return list.toArray<Entity>(arrayOfNulls<Entity>(list.size))
    }

    private fun makeEntity2(data: Entities): Array<Entity> {
        val list = ArrayList<Entity>()
        setEntity2(list, data.URL)
        setEntity2(list, data.Media)
        return list.toArray<Entity>(arrayOfNulls<Entity>(list.size))
    }

    private fun setEntity(list: ArrayList<Entity>, data: Array<URLEntity>?) {
        data?.forEach {
            e ->
            list.add(Entity(EntityType.URL, e.displayUrl, e.start, e.end))
        }
    }

    private fun setEntity(list: ArrayList<Entity>, data: Array<MediaEntity>?) {
        data?.forEach {
            e ->
            list.add(Entity(EntityType.URL, e.displayUrl, e.start, e.end))
        }
    }

    private fun setEntity(list: ArrayList<Entity>, data: Array<UserMentionEntity>?) {
        data?.forEach {
            e ->
            list.add(Entity(EntityType.ID, e.screenName, e.start, e.end))
        }
    }

    private fun setEntity(list: ArrayList<Entity>, data: Array<HashtagEntity>?) {
        data?.forEach {
            e ->
            list.add(Entity(EntityType.TAG, e.text, e.start, e.end))
        }
    }

    private fun setEntity(list: ArrayList<Entity>, data: Array<SymbolEntity>?) {
        data?.forEach {
            e ->
            list.add(Entity(EntityType.SYMBOL, e.text, e.start, e.end))
        }
    }

    private fun setEntity2(list: ArrayList<Entity>, data: Array<URLEntity>?) {
        data?.forEach {
            e ->
            list.add(Entity(EntityType.URL, e.expandedUrl, e.start, e.end))
        }
    }

    private fun setEntity2(list: ArrayList<Entity>, data: Array<MediaEntity>?) {
        data?.forEach {
            e ->
            list.add(Entity(EntityType.URL, e.expandedUrl, e.start, e.end))
        }
    }

    private val comparator = EntityComparator()

    private class EntityComparator : Comparator<Entity> {

        override fun compare(s: Entity, t: Entity): Int {
            return (s.start - t.start)
        }
    }

    internal class Entity(internal val type: EntityType, internal val to: String, internal val start: Int, internal val end: Int)

    internal enum class EntityType {
        URL, ID, TAG, SYMBOL
    }

    private val escapeMap = HashMap<String, Char>()

    init {
        escapeMap.put("&quot;", '\u0022')
        escapeMap.put("&amp;", '\u0026')
        escapeMap.put("&lt;", '\u003C')
        escapeMap.put("&gt;", '\u003E')
        escapeMap.put("&nbsp;", '\u00A0')
        escapeMap.put("&iexcl;", '\u00A1')
        escapeMap.put("&cent;", '\u00A2')
        escapeMap.put("&pound;", '\u00A3')
        escapeMap.put("&curren;", '\u00A4')
        escapeMap.put("&yen;", '\u00A5')
        escapeMap.put("&brvbar;", '\u00A6')
        escapeMap.put("&sect;", '\u00A7')
        escapeMap.put("&uml;", '\u00A8')
        escapeMap.put("&copy;", '\u00A9')
        escapeMap.put("&ordf;", '\u00AA')
        escapeMap.put("&laquo;", '\u00AB')
        escapeMap.put("&not;", '\u00AC')
        escapeMap.put("&shy;", '\u00AD')
        escapeMap.put("&reg;", '\u00AE')
        escapeMap.put("&macr;", '\u00AF')
        escapeMap.put("&deg;", '\u00B0')
        escapeMap.put("&plusmn;", '\u00B1')
        escapeMap.put("&sup2;", '\u00B2')
        escapeMap.put("&sup3;", '\u00B3')
        escapeMap.put("&acute;", '\u00B4')
        escapeMap.put("&micro;", '\u00B5')
        escapeMap.put("&para;", '\u00B6')
        escapeMap.put("&middot;", '\u00B7')
        escapeMap.put("&cedil;", '\u00B8')
        escapeMap.put("&sup1;", '\u00B9')
        escapeMap.put("&ordm;", '\u00BA')
        escapeMap.put("&raquo;", '\u00BB')
        escapeMap.put("&frac14;", '\u00BC')
        escapeMap.put("&frac12;", '\u00BD')
        escapeMap.put("&frac34;", '\u00BE')
        escapeMap.put("&iquest;", '\u00BF')
        escapeMap.put("&Agrave;", '\u00C0')
        escapeMap.put("&Aacute;", '\u00C1')
        escapeMap.put("&Acirc;", '\u00C2')
        escapeMap.put("&Atilde;", '\u00C3')
        escapeMap.put("&Auml;", '\u00C4')
        escapeMap.put("&Aring;", '\u00C5')
        escapeMap.put("&AElig;", '\u00C6')
        escapeMap.put("&Ccedil;", '\u00C7')
        escapeMap.put("&Egrave;", '\u00C8')
        escapeMap.put("&Eacute;", '\u00C9')
        escapeMap.put("&Ecirc;", '\u00CA')
        escapeMap.put("&Euml;", '\u00CB')
        escapeMap.put("&Igrave;", '\u00CC')
        escapeMap.put("&Iacute;", '\u00CD')
        escapeMap.put("&Icirc;", '\u00CE')
        escapeMap.put("&Iuml;", '\u00CF')
        escapeMap.put("&ETH;", '\u00D0')
        escapeMap.put("&Ntilde;", '\u00D1')
        escapeMap.put("&Ograve;", '\u00D2')
        escapeMap.put("&Oacute;", '\u00D3')
        escapeMap.put("&Ocirc;", '\u00D4')
        escapeMap.put("&Otilde;", '\u00D5')
        escapeMap.put("&Ouml;", '\u00D6')
        escapeMap.put("&times;", '\u00D7')
        escapeMap.put("&Oslash;", '\u00D8')
        escapeMap.put("&Ugrave;", '\u00D9')
        escapeMap.put("&Uacute;", '\u00DA')
        escapeMap.put("&Ucirc;", '\u00DB')
        escapeMap.put("&Uuml;", '\u00DC')
        escapeMap.put("&Yacute;", '\u00DD')
        escapeMap.put("&THORN;", '\u00DE')
        escapeMap.put("&szlig;", '\u00DF')
        escapeMap.put("&agrave;", '\u00E0')
        escapeMap.put("&aacute;", '\u00E1')
        escapeMap.put("&acirc;", '\u00E2')
        escapeMap.put("&atilde;", '\u00E3')
        escapeMap.put("&auml;", '\u00E4')
        escapeMap.put("&aring;", '\u00E5')
        escapeMap.put("&aelig;", '\u00E6')
        escapeMap.put("&ccedil;", '\u00E7')
        escapeMap.put("&egrave;", '\u00E8')
        escapeMap.put("&eacute;", '\u00E9')
        escapeMap.put("&ecirc;", '\u00EA')
        escapeMap.put("&euml;", '\u00EB')
        escapeMap.put("&igrave;", '\u00EC')
        escapeMap.put("&iacute;", '\u00ED')
        escapeMap.put("&icirc;", '\u00EE')
        escapeMap.put("&iuml;", '\u00EF')
        escapeMap.put("&eth;", '\u00F0')
        escapeMap.put("&ntilde;", '\u00F1')
        escapeMap.put("&ograve;", '\u00F2')
        escapeMap.put("&oacute;", '\u00F3')
        escapeMap.put("&ocirc;", '\u00F4')
        escapeMap.put("&otilde;", '\u00F5')
        escapeMap.put("&ouml;", '\u00F6')
        escapeMap.put("&divide;", '\u00F7')
        escapeMap.put("&oslash;", '\u00F8')
        escapeMap.put("&ugrave;", '\u00F9')
        escapeMap.put("&uacute;", '\u00FA')
        escapeMap.put("&ucirc;", '\u00FB')
        escapeMap.put("&uuml;", '\u00FC')
        escapeMap.put("&yacute;", '\u00FD')
        escapeMap.put("&thorn;", '\u00FE')
        escapeMap.put("&yuml;", '\u00FF')
        escapeMap.put("&OElig;", '\u0152')
        escapeMap.put("&oelig;", '\u0153')
        escapeMap.put("&Scaron;", '\u0160')
        escapeMap.put("&scaron;", '\u0161')
        escapeMap.put("&Yuml;", '\u0178')
        escapeMap.put("&fnof;", '\u0192')
        escapeMap.put("&circ;", '\u02C6')
        escapeMap.put("&tilde;", '\u02DC')
        escapeMap.put("&Alpha;", '\u0391')
        escapeMap.put("&Beta;", '\u0392')
        escapeMap.put("&Gamma;", '\u0393')
        escapeMap.put("&Delta;", '\u0394')
        escapeMap.put("&Epsilon;", '\u0395')
        escapeMap.put("&Zeta;", '\u0396')
        escapeMap.put("&Eta;", '\u0397')
        escapeMap.put("&Theta;", '\u0398')
        escapeMap.put("&Iota;", '\u0399')
        escapeMap.put("&Kappa;", '\u039A')
        escapeMap.put("&Lambda;", '\u039B')
        escapeMap.put("&Mu;", '\u039C')
        escapeMap.put("&Nu;", '\u039D')
        escapeMap.put("&Xi;", '\u039E')
        escapeMap.put("&Omicron;", '\u039F')
        escapeMap.put("&Pi;", '\u03A0')
        escapeMap.put("&Rho;", '\u03A1')
        escapeMap.put("&Sigma;", '\u03A3')
        escapeMap.put("&Tau;", '\u03A4')
        escapeMap.put("&Upsilon;", '\u03A5')
        escapeMap.put("&Phi;", '\u03A6')
        escapeMap.put("&Chi;", '\u03A7')
        escapeMap.put("&Psi;", '\u03A8')
        escapeMap.put("&Omega;", '\u03A9')
        escapeMap.put("&alpha;", '\u03B1')
        escapeMap.put("&beta;", '\u03B2')
        escapeMap.put("&gamma;", '\u03B3')
        escapeMap.put("&delta;", '\u03B4')
        escapeMap.put("&epsilon;", '\u03B5')
        escapeMap.put("&zeta;", '\u03B6')
        escapeMap.put("&eta;", '\u03B7')
        escapeMap.put("&theta;", '\u03B8')
        escapeMap.put("&iota;", '\u03B9')
        escapeMap.put("&kappa;", '\u03BA')
        escapeMap.put("&lambda;", '\u03BB')
        escapeMap.put("&mu;", '\u03BC')
        escapeMap.put("&nu;", '\u03BD')
        escapeMap.put("&xi;", '\u03BE')
        escapeMap.put("&omicron;", '\u03BF')
        escapeMap.put("&pi;", '\u03C0')
        escapeMap.put("&rho;", '\u03C1')
        escapeMap.put("&sigmaf;", '\u03C2')
        escapeMap.put("&sigma;", '\u03C3')
        escapeMap.put("&tau;", '\u03C4')
        escapeMap.put("&upsilon;", '\u03C5')
        escapeMap.put("&phi;", '\u03C6')
        escapeMap.put("&chi;", '\u03C7')
        escapeMap.put("&psi;", '\u03C8')
        escapeMap.put("&omega;", '\u03C9')
        escapeMap.put("&thetasym;", '\u03D1')
        escapeMap.put("&upsih;", '\u03D2')
        escapeMap.put("&piv;", '\u03D6')
        escapeMap.put("&bull;", '\u2022')
        escapeMap.put("&hellip;", '\u2026')
        escapeMap.put("&prime;", '\u2032')
        escapeMap.put("&Prime;", '\u2033')
        escapeMap.put("&oline;", '\u203E')
        escapeMap.put("&frasl;", '\u2044')
        escapeMap.put("&weierp;", '\u2118')
        escapeMap.put("&image;", '\u2111')
        escapeMap.put("&real;", '\u211C')
        escapeMap.put("&trade;", '\u2122')
        escapeMap.put("&alefsym;", '\u2135')
        escapeMap.put("&larr;", '\u2190')
        escapeMap.put("&uarr;", '\u2191')
        escapeMap.put("&rarr;", '\u2192')
        escapeMap.put("&darr;", '\u2193')
        escapeMap.put("&harr;", '\u2194')
        escapeMap.put("&crarr;", '\u21B5')
        escapeMap.put("&lArr;", '\u21D0')
        escapeMap.put("&uArr;", '\u21D1')
        escapeMap.put("&rArr;", '\u21D2')
        escapeMap.put("&dArr;", '\u21D3')
        escapeMap.put("&hArr;", '\u21D4')
        escapeMap.put("&forall;", '\u2200')
        escapeMap.put("&part;", '\u2202')
        escapeMap.put("&exist;", '\u2203')
        escapeMap.put("&empty;", '\u2205')
        escapeMap.put("&nabla;", '\u2207')
        escapeMap.put("&isin;", '\u2208')
        escapeMap.put("&notin;", '\u2209')
        escapeMap.put("&ni;", '\u220B')
        escapeMap.put("&prod;", '\u220F')
        escapeMap.put("&sum;", '\u2211')
        escapeMap.put("&minus;", '\u2212')
        escapeMap.put("&lowast;", '\u2217')
        escapeMap.put("&radic;", '\u221A')
        escapeMap.put("&prop;", '\u221D')
        escapeMap.put("&infin;", '\u221E')
        escapeMap.put("&ang;", '\u2220')
        escapeMap.put("&and;", '\u2227')
        escapeMap.put("&or;", '\u2228')
        escapeMap.put("&cap;", '\u2229')
        escapeMap.put("&cup;", '\u222A')
        escapeMap.put("&int;", '\u222B')
        escapeMap.put("&there4;", '\u2234')
        escapeMap.put("&sim;", '\u223C')
        escapeMap.put("&cong;", '\u2245')
        escapeMap.put("&asymp;", '\u2248')
        escapeMap.put("&ne;", '\u2260')
        escapeMap.put("&equiv;", '\u2261')
        escapeMap.put("&le;", '\u2264')
        escapeMap.put("&ge;", '\u2265')
        escapeMap.put("&sub;", '\u2282')
        escapeMap.put("&sup;", '\u2283')
        escapeMap.put("&nsub;", '\u2284')
        escapeMap.put("&sube;", '\u2286')
        escapeMap.put("&supe;", '\u2287')
        escapeMap.put("&oplus;", '\u2295')
        escapeMap.put("&otimes;", '\u2297')
        escapeMap.put("&perp;", '\u22A5')
        escapeMap.put("&sdot;", '\u22C5')
        escapeMap.put("&lceil;", '\u2308')
        escapeMap.put("&rceil;", '\u2309')
        escapeMap.put("&lfloor;", '\u230A')
        escapeMap.put("&rfloor;", '\u230B')
        escapeMap.put("&lang;", '\u2329')
        escapeMap.put("&rang;", '\u232A')
        escapeMap.put("&loz;", '\u25CA')
        escapeMap.put("&spades;", '\u2660')
        escapeMap.put("&clubs;", '\u2663')
        escapeMap.put("&hearts;", '\u2665')
        escapeMap.put("&diams;", '\u2666')
        escapeMap.put("&ensp;", '\u2002')
        escapeMap.put("&emsp;", '\u2003')
        escapeMap.put("&thinsp;", '\u2009')
        escapeMap.put("&zwnj;", '\u200C')
        escapeMap.put("&zwj;", '\u200D')
        escapeMap.put("&lrm;", '\u200E')
        escapeMap.put("&rlm;", '\u200F')
        escapeMap.put("&ndash;", '\u2013')
        escapeMap.put("&mdash;", '\u2014')
        escapeMap.put("&lsquo;", '\u2018')
        escapeMap.put("&rsquo;", '\u2019')
        escapeMap.put("&sbquo;", '\u201A')
        escapeMap.put("&ldquo;", '\u201C')
        escapeMap.put("&rdquo;", '\u201D')
        escapeMap.put("&bdquo;", '\u201E')
        escapeMap.put("&dagger;", '\u2020')
        escapeMap.put("&Dagger;", '\u2021')
        escapeMap.put("&permil;", '\u2030')
        escapeMap.put("&lsaquo;", '\u2039')
    }

}
