package round2.oa;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Twitter Phone Interview Question
 */
public class InterleavingPlaylist implements Playlist{
    Queue<Iterator<Integer>> queue;

    public InterleavingPlaylist(List<List<Integer>> trackList) {
        queue = new LinkedList<>();
        for (List<Integer> track : trackList) {
            queue.offer(track.iterator());
        }
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }

    @Override
    public int getNext() {
        Iterator<Integer> cur = queue.poll();
        int curTrack = (int) cur.next();
        if (cur.hasNext()) queue.offer(cur);
        return curTrack;
    }
}

interface Playlist {
    boolean hasNext();
    int getNext();
}