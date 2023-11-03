package programmers.src;

import java.time.LocalTime;
import java.util.Comparator;
import java.util.PriorityQueue;

public class 호텔대실 {
    class TimeSchedule{
        String startTime;
        String endTime;

        TimeSchedule(String s, String e){
            startTime = s;
            endTime = e;
        }

        @Override
        public String toString(){
            return startTime + " " + endTime;
        }

    }
    public int solution(String[][] book_time) {
        int answer = 0;
        PriorityQueue<TimeSchedule> 손님= new PriorityQueue<>(new Comparator<TimeSchedule>() {
            @Override
            public int compare(TimeSchedule o1, TimeSchedule o2) {
                if(o1.startTime.compareTo(o2.startTime) == 0){
                    return o1.endTime.compareTo(o2.endTime);
                }

                return o1.startTime.compareTo(o2.startTime);
            }
        });
        PriorityQueue<TimeSchedule> 객실= new PriorityQueue<>(new Comparator<TimeSchedule>() {
            @Override
            public int compare(TimeSchedule o1, TimeSchedule o2) {
                return o1.endTime.compareTo(o2.endTime);
            }
        });


        for (int i = 0; i<book_time.length; i++){
            손님.add(new TimeSchedule(book_time[i][0], book_time[i][1]));
        }

        while (!손님.isEmpty()){
            TimeSchedule 먼저온손님 = 손님.poll();
            LocalTime startTime = LocalTime.parse(먼저온손님.startTime).minusMinutes(10);

            if(startTime.isAfter(LocalTime.parse(먼저온손님.startTime))){
                startTime = LocalTime.parse("00:00");
            }

            while (!객실.isEmpty()){
                LocalTime 이미들어간손님중가장빨리나오는손님 = LocalTime.parse(객실.peek().endTime);

                if (!startTime.isBefore(이미들어간손님중가장빨리나오는손님)){
                    객실.poll();
                } else {
                    break;
                }
            }

            객실.add(먼저온손님);

            if(객실.size() > answer){
                answer = 객실.size();
            }
        }

        return answer;
    }
}
