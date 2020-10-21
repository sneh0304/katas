package kata;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

public class User {
	private String name;
	private HashSet<User> followSet = new HashSet<User>();
	private LinkedHashMap<String, Instant> messages = new LinkedHashMap<String, Instant>();
	
	public User(String name) {
		this.name = name;
		this.follow(this);
	}
	public String getName() {
		return name;
	}
	public void publishMessage(String msg) {
		messages.put(msg, Instant.now());
	}
	public void follow(User u) {
		followSet.add(u);
	}
	public Boolean isFollowing(User u) {
		return this.followSet.contains(u);
	}
	public void viewTimeline(User u) {
		if (!this.isFollowing(u)) {
			System.out.println("\nCan't see the timeline of someone you do not follow. Follow " + u.getName() + " first!");
			return;
		}
		u.timeline();
	}
	private void timeline() {
		System.out.println("\nViewing timeline of " + this.getName());
		if (messages.isEmpty())
			return;
		if (messages.size() == 1)
			System.out.println(messages.keySet().toArray()[0]);
		else {
			List<String> reversedKey = new ArrayList<String>(messages.keySet());
			Collections.reverse(reversedKey);
			Instant end = Instant.now();
			for (String key : reversedKey) {
				Instant start = messages.get(key);
				Duration timeElapsed = Duration.between(start, end);
				if (timeElapsed.toMinutes() < 1) {
					long sec = timeElapsed.toMillis() / 1000;
					if (sec == 1)
						System.out.println(key + " (" + sec + " second ago)");
					else
						System.out.println(key + " (" + sec + " seconds ago)");
				}
				else
				{
					int min = Math.round(timeElapsed.toMinutes());
					if (min == 1)
						System.out.println(key + " (" + min + " minute ago)");
					else
						System.out.println(key + " (" + min + " minutes ago)");
				}
			}
		}
	}
	class bufferComparator implements Comparator<String> {
        public int compare(String s1, String s2) {
            Long a = Long.parseLong(s1.split("##", 2)[0]);
            Long b = Long.parseLong(s2.split("##", 2)[0]);
            if (a < b)
                return -1;
            else if (a > b)
                return 1;
            return 0;
        }
    }
	public void viewWall() {
		PriorityQueue<String> buffer = new PriorityQueue<String>(25, new bufferComparator());
		for (User u : this.followSet) {
			Instant end = Instant.now();
			for (String key : u.messages.keySet()) {
				Instant start = u.messages.get(key);
				Long timeElapsed = Duration.between(start, end).toMillis() / 1000;
				String name = u.getName();
				String input = timeElapsed + "##" + name + "##" + key;
				buffer.add(input);
			}
		}
		System.out.println("\nViewing wall of " + this.getName());
		while (!buffer.isEmpty()) {
			String[] splittedMessage = buffer.poll().split("##", 3);
			Long time = Long.parseLong(splittedMessage[0]);
			String name = splittedMessage[1];
			String msg = splittedMessage[2];
			if (time < 60) {
				if (time == 1)
					System.out.println(name + " - " + msg + " (" + time + " second ago)");
				else
					System.out.println(name + " - " + msg + " (" + time + " seconds ago)");
			} else {
				int min = Math.round(time / 60);
				if (time == 1)
					System.out.println(name + " - " + msg + " (" + min + " minute ago)");
				else
					System.out.println(name + " - " + msg + " (" + min + " minutes ago)");
			}
		}
	}
}