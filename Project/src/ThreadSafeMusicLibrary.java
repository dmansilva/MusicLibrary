import java.nio.file.Path;


public class ThreadSafeMusicLibrary extends MusicLibrary {
	
	private Lock lock;
	
	public ThreadSafeMusicLibrary () {
		
		super();
		this.lock = new Lock();
		
	}


	
	public void addSong(Song song) {
		
		lock.lockWrite();
		super.addSong(song);
		lock.unlockWrite();
		
	}
	
	// @Override
	
	public void outputByTitle(Path path) {
		
		lock.lockRead();
		super.outputByTitle(path);
		lock.unlockRead();
		
	}
	
	// @Override
	
	public void outputByArtist(Path path) {
		
		lock.lockRead();
		super.outputByArtist(path);
		lock.unlockRead();
		
	}
	
	// @Override
	
	public void outputByTag(Path path) {
		
		lock.lockRead();
		super.outputByTag(path);
		lock.unlockRead();
		
	}

}
