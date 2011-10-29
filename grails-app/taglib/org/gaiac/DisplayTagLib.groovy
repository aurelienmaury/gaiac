package org.gaiac

class DisplayTagLib {

  static namespace = 'display'

  static def kilo = 1024

  static def orderedMsgCodes = [ 'common.bytes.label',
                                'common.kbytes.label',
                                'common.mbytes.label',
                                'common.gbytes.label',
                                'common.tbytes.label',
                                'common.pbytes.label' ]

  def fileSize = { attrs, body ->
    if (attrs.value) {
      def res = attrs.value
      def i = 0
      while (res > kilo) {
          res /= kilo
          i++
      }

      res = new Double(res).round(2)
      def unit = message(code: orderedMsgCodes[i])
      out << "${res} ${unit}"
    }
  }

  def labelCut = { attrs, body ->
    if (attrs.max && attrs.max.isInteger()) {
      def max = new Integer(attrs.max) - 3
      if (attrs.value.size() > max) {
        def res = attrs.value.substring(0, max) + "..."
        out << res
      } else {
        out << attrs.value
      }
    }
  }
}
