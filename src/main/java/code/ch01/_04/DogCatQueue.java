package code.ch01._04;

import java.util.Queue;

public class DogCatQueue {
    private Queue<PetEnterQueue> dogQ;
    private Queue<PetEnterQueue> catQ;
    private long count;//时间戳，比较猫和狗的进入时间

    public DogCatQueue(Queue<PetEnterQueue> dogQ, Queue<PetEnterQueue> catQ, long count) {
        this.dogQ = dogQ;
        this.catQ = catQ;
        this.count = count;
    }

    /**
     * 将猫或狗直接压入队列
     *
     * @param pet
     */
    public void add(Pet pet) {
        if (pet.getType().equals("dog")) {
            this.dogQ.add(new PetEnterQueue(pet, this.count++));
        } else if (pet.getType().equals("cat")) {
            this.catQ.add(new PetEnterQueue(pet, this.count++));
        } else {
            throw new RuntimeException("err,not dog or cat");
        }
    }

    /**
     * 将队列中的实例按先后顺序直接弹出
     *
     * @return
     */
    public Pet pollAll() {
        if (!this.dogQ.isEmpty() && !this.catQ.isEmpty()) {
            //比较猫狗队列队头进入时间，弹出先进的，即count较小的
            if (this.catQ.peek().getCount() < this.dogQ.peek().getCount()) {
                return this.catQ.poll().getPet();
            } else {
                return this.dogQ.poll().getPet();
            }
        } else if (!this.dogQ.isEmpty()) {
            return this.dogQ.poll().getPet();
        } else if (!this.catQ.isEmpty()) {
            return this.catQ.poll().getPet();
        } else {
            throw new RuntimeException("err, queue is empty");
        }
    }

    /**
     * 弹出狗队列的实例
     * @return
     */
    public Dog pollDog() {
        if (!this.isDogQueueEmpty()) {
            return (Dog) this.dogQ.poll().getPet();
        } else {
            throw new RuntimeException("Dog queue is empty!");
        }
    }

    /**
     * 弹出猫队列的实例
     * @return
     */
    public Cat pollCat() {
        if (!this.isCatQueueEmpty()) {
            return (Cat) this.catQ.poll().getPet();
        } else {
            throw new RuntimeException("Cat queue is empty!");
        }
    }

    public boolean isEmpty() {
        return this.dogQ.isEmpty() && this.catQ.isEmpty();
    }

    private boolean isDogQueueEmpty() {
        return this.dogQ.isEmpty();
    }

    private boolean isCatQueueEmpty() {
        return this.catQ.isEmpty();
    }
}
