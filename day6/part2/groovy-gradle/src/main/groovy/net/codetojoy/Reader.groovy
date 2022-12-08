
package net.codetojoy

class Reader {
    static final NUM_CHARS_FOR_PACKET_MARKER = 4
    static final NUM_CHARS_FOR_MESSAGE_MARKER = 14

    def packetStopIndex = -5150
    def packetBuffer = new Buffer(NUM_CHARS_FOR_PACKET_MARKER)
    def packetInfo = new MarkerInfo()

    def messageStopIndex = -5150
    def messageBuffer = new Buffer(NUM_CHARS_FOR_MESSAGE_MARKER)
    def messageInfo = new MarkerInfo()

    def processCharForPacket(def c, def index) {
        packetBuffer.add(c)
        packetInfo.found = isPacketMarkerFound()

        if (packetInfo.found) {
            packetInfo.stopIndex = index + 1
        }
    }

    def processCharForMessage(def c, def index) {
        messageBuffer.add(c)
        messageInfo.found = isMessageMarkerFound()

        if (messageInfo.found) {
            messageInfo.stopIndex = index + 1
        }
    }

    def read(def s) {
        def chars = (s as List)
        chars.eachWithIndex { c, index ->
            if (!packetInfo.found) {
                processCharForPacket(c, index)
            }
            if (!messageInfo.found) {
                processCharForMessage(c, index)
            }
        }
    }

    def getPacketStopIndex() {
        assert packetInfo.found
        packetInfo.stopIndex
    }

    def isPacketMarkerFound() {
        packetBuffer.getNumUniqueElements() == NUM_CHARS_FOR_PACKET_MARKER
    }

    def getMessageStopIndex() {
        assert messageInfo.found
        messageInfo.stopIndex
    }

    def isMessageMarkerFound() {
        messageBuffer.getNumUniqueElements() == NUM_CHARS_FOR_MESSAGE_MARKER
    }
}
