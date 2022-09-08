package fun.re1ife.leetcode;

import java.util.*;

/**
 * Don't worry, be happy
 *
 * 2
 * [[43,44],[34,36],[11,47],[1,8],[30,33],[45,48],[23,41],[29,30]]
 *
 * @author re1ife
 * @date 2022/09/04 11:10
 **/
public class Code6170 {

    public static void main(String[] args) {
        int[][] meetings = {{43,44},{34,36},{11,47},{1,8},{30,33},{45,48},{23,41},{29,30}};
        //                 [[43,44],[34,36],,,[30,33],[45,48],[23,41],[29,30]]
        int n = 2;
        System.out.println(mostBooked(n, meetings));
    }

    public static int mostBooked(int n, int[][] meetings) {
        List<MeetingRoom> list = new ArrayList<>();
        for (int i = 0; i < n; i ++) {
            list.add(new MeetingRoom(i));
        }
        HeapGreater<Meeting> heap = new HeapGreater<Meeting>(new MyComparator());
        for (int i = 0; i < meetings.length; i ++) {
            Meeting cur = new Meeting(meetings[i][0], meetings[i][1]);
            heap.push(cur);
        }
        HeapGreater<MeetingRoom> heapM = new HeapGreater<>(new MyRoomComparator());
        for (MeetingRoom meetingRoom : list) {
            heapM.push(meetingRoom);
        }

        int curTime = 0;
        while (!heap.isEmpty()) {
            Meeting curMeeting = heap.pop();
            MeetingRoom curMeetingRoom = heapM.peek();
            if (curTime < curMeetingRoom.finishTime) {
                curTime = curMeetingRoom.finishTime;
                heap.push(curMeeting);
            } else {
                if (curMeeting.startTime <= curTime) {
                    MeetingRoom pop = heapM.pop();
                    pop.startMeeting(curMeeting, curTime);
                    heapM.push(pop);
                } else {
                    curTime = curMeeting.startTime;
                    heap.push(curMeeting);
                }
            }
        }
        MeetingRoom ans = null;
        while (!heapM.isEmpty()) {
            MeetingRoom cur = heapM.pop();
            if (ans == null || (ans.used <= cur.used && ans.roomNum > cur.roomNum)) {
                ans = cur;
            }
        }
        return ans == null ? 0 : ans.roomNum;

    }

    public static class HeapGreater<V> {

        private List<V> heap;
        private Map<V, Integer> indexMap;
        private int heapSize;
        private Comparator<? super V> comparator;

        public HeapGreater(Comparator<? super V> c) {
            comparator = c;
            heap = new ArrayList<>();
            indexMap = new HashMap<>();
            heapSize = 0;
        }

        public int size() {
            return heapSize;
        }

        public boolean isEmpty() {
            return heapSize == 0;
        }

        public boolean contains(V obj) {
            return indexMap.containsKey(obj);
        }

        public void push(V val) {
            heap.add(val);
            indexMap.put(val, heapSize);
            heapInsert(heapSize++);
        }

        public V peek() {
            return heap.get(0);
        }

        public V pop() {
            V ans = heap.get(0);
            swap(0, heapSize - 1);
            indexMap.remove(ans);
            heap.remove(--heapSize);
            heapify(0);
            return ans;
        }

        public void remove(V obj) {
            V replace = heap.get(heapSize - 1);
            int index = indexMap.get(obj);
            indexMap.remove(obj);
            heap.remove(--heapSize);
            if (obj != replace) {
                heap.set(index, replace);
                indexMap.put(replace, index);
                heapInsert(index);
                heapify(index);

            }
        }

        public void heapInsert(int index) {
            while (comparator.compare(heap.get(index), heap.get((index - 1) / 2)) < 0) {
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        public void heapify(int index) {
            int left = index * 2 + 1;
            while (left < heapSize) {
                int best = left + 1 < heapSize && comparator.compare(heap.get(left + 1), heap.get(left)) < 0 ? left + 1 : left;
                best = comparator.compare(heap.get(best), heap.get(index)) < 0 ? best : index;
                if (best == index) {
                    return;
                }
                swap(best, index);
                index = best;
                left = index * 2 + 1;
            }
        }

        public void swap(int i, int j) {
            V v1 = heap.get(i);
            V v2 = heap.get(j);
            indexMap.put(v1, j);
            indexMap.put(v2, i);
            heap.set(i, v2);
            heap.set(j, v1);
        }

    }

    public static class MyComparator implements Comparator<Meeting> {
        @Override
        public int compare(Meeting o1, Meeting o2) {
            return o1.startTime - o2.startTime;
        }
    }

    public static class MyRoomComparator implements Comparator<MeetingRoom> {

        @Override
        public int compare(MeetingRoom o1, MeetingRoom o2) {
            return o1.finishTime == o2.finishTime ? o1.roomNum - o2.roomNum : o1.finishTime - o2.finishTime;
        }
    }

    public static class MeetingRoom {
        int roomNum;
        int finishTime;
        int used;

        public MeetingRoom(int i) {
            used = 0;
            finishTime = 0;
            roomNum = i;
        }

        public void startMeeting(Meeting meeting, int curTime) {
            used ++;
            finishTime = curTime + meeting.last;
        }

    }

    public static class Meeting {
        int startTime;
        int last;
        public Meeting(int s, int e) {
            last = e - s;
            startTime = s;
        }
    }
}
