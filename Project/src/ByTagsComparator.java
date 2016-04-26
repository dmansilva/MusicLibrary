import java.util.Comparator;

public class ByTagsComparator implements Comparator<Song> {

	@Override
	public int compare(Song user1, Song user2) {
		
		
		if (user1.getTrackId().equals(user2.getTrackId())) {
			
			if (user1.getArtist().equals(user2.getArtist())) {
				
				return user1.getTitle().compareTo(user2.getTitle());
			}
			
			else {
				
				return user1.getArtist().compareTo(user2.getArtist());
			}
		}
		else {
			
			return user1.getTrackId().compareTo(user2.getTrackId());
		}
		
	}
	
	

}
