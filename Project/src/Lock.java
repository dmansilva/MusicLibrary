
import java.util.HashMap;

/**
 * A read/write lock that allows multiple readers, disallows multiple writers, and allows a writer to 
 * acquire a read lock while holding the write lock. 
 * 
 */
public class Lock {

	//Declare data members here!
	private HashMap<Long, Integer> numOfReaders;
	private HashMap<Long, Integer> numOfWriters;
	
	

	/**
	 * Construct a new ReentrantLock.
	 */
	public Lock() {
		
		this.numOfReaders = new HashMap<>();
		this.numOfWriters = new HashMap<>();
		
		
	}

	/**
	 * Returns true if the invoking thread holds a read lock.
	 * @return
	 */
	public synchronized boolean hasRead() {
		
//TODO: revise condition		
		if (this.numOfReaders.containsKey(Thread.currentThread().getId()) && this.numOfReaders.get(Thread.currentThread().getId()) > 0) {
			return true;
		}
		return false;
	}

	/**
	 * Returns true if the invoking thread holds a write lock.
	 * @return
	 */
	public synchronized boolean hasWrite() {
		
//TODO: revise condition		
		if (this.numOfWriters.containsKey(Thread.currentThread().getId()) && this.numOfWriters.get(Thread.currentThread().getId()) > 0) {
			return true;
		}
		return false;
	}

	/**
	 * Non-blocking method that attempts to acquire the read lock.
	 * Returns true if successful.
	 * @return
	 */
	public synchronized boolean tryLockRead() {
		
		if ((this.numOfWriters.size() == 0) || this.numOfWriters.size() >= 1 && this.numOfWriters.containsKey(Thread.currentThread().getId())) {
		
//TODO: you are granting two locks the first time around		
			if (this.numOfReaders.get(Thread.currentThread().getId()) == null) {
				int value = 1;
				this.numOfReaders.put(Thread.currentThread().getId(), value);
			}
		
			int value = this.numOfReaders.get(Thread.currentThread().getId());
			value++;
			this.numOfReaders.put(Thread.currentThread().getId(), value);
			return true;
		}
		
		return false;

	}

	/**
	 * Non-blocking method that attempts to acquire the write lock.
	 * Returns true if successful.
	 * @return
	 */	
	public synchronized boolean tryLockWrite() {
// a thread could get a write lock if there are no other thread holding a read lock
<<<<<<< Updated upstream

//TODO: hasRead and hasWrite only check to see whether the calling thread has the read/write lock		
=======
		// this.numOfReaders.size != 0 && this.numOfWriters.size !=0 || hasWrite and the thread is me
>>>>>>> Stashed changes
		if ((!hasRead() && !hasWrite()) || hasWrite()) {
			if (this.numOfWriters.get(Thread.currentThread().getId()) == null) {
				int value = 1;
				this.numOfWriters.put(Thread.currentThread().getId(), value);
			}
			
			int value = this.numOfWriters.get(Thread.currentThread().getId());
			value++;
			this.numOfWriters.put(Thread.currentThread().getId(), value);
			return true;
		}
		
		return false;

	}

	/**
	 * Blocking method that will return only when the read lock has been 
	 * acquired.
	 */	 
	public synchronized void lockRead() {
		
		
		
		while (!tryLockRead()) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
		
		
	}

	/**
	 * Releases the read lock held by the calling thread. Other threads may continue
	 * to hold a read lock.
	 */
	public synchronized void unlockRead() {
		
		
		if (hasRead()) {
			
			int value = this.numOfReaders.get(Thread.currentThread().getId());
			if (value > 1) {
				value--;
				this.numOfReaders.put(Thread.currentThread().getId(), value);
				
			}

//TODO: use else		
			
			if (value == 1) {
				this.numOfReaders.remove(Thread.currentThread().getId());
				
				notifyAll();
			}
				
			
		}
		
			
	}

	/**
	 * Blocking method that will return only when the write lock has been 
	 * acquired.
	 */
	public synchronized void lockWrite() {
		
		
		
		while (!tryLockWrite()) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();			
			}
		}

	}

	/**
	 * Releases the write lock held by the calling thread. The calling thread may continue to hold
	 * a read lock.
	 */
	public synchronized void unlockWrite() {
		
//TODO: check to see if you are a writer		
		int value = this.numOfWriters.get(Thread.currentThread().getId());
		value--;
		this.numOfWriters.put(Thread.currentThread().getId(), value);
		
		if (value == 1) {
			this.numOfWriters.remove(Thread.currentThread().getId());
			notifyAll();
		}
		
	}
}
