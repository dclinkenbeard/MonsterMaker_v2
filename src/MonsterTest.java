import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class MonsterTest {

    Monster c;
    Monster d;

    @BeforeEach
    void setUp() {
        d = new Dragon("bob");
        c = new CookieMonster("hankM");
    }

    @AfterEach
    void tearDown() {
        c = null;
        d = null;

    }

    @Test
    void equality(){
        assertNotEquals(c, d);
        Monster c2 = new CookieMonster("hankM");
        assertEquals(c,c2);
    }

    @Test
    void addMonster() {
        int currentCount = Monster.getMonsterCount();
        Monster m = new CookieMonster("foo");
        assertEquals(currentCount+1,Monster.getMonsterCount());

    }

    @Test
    void getName() {
        assertEquals("hankM", c.getName());
        assertEquals("bob",d.getName());
    }

    @Test
    void getMonsterCount() {
        Random random = new Random();
        List<Monster> monsters = new ArrayList<>();
        int monsterNumber = random.nextInt(10);

        for(int i = 0; i < monsterNumber; i++){
            if(i%2 == 0){
                monsters.add(new Dragon("testDragon"+i));
            }else{
                monsters.add(new CookieMonster("testCM"+i));
            }
        }

        assertNotEquals(monsterNumber, Monster.getMonsterCount());
        assertEquals(2+monsterNumber, Monster.getMonsterCount());
        assertNotEquals(1+monsterNumber, Monster.getMonsterCount());
        CookieMonster c2 = new CookieMonster();
        assertEquals(3+monsterNumber, Monster.getMonsterCount());
        assertNotEquals(4+monsterNumber, Monster.getMonsterCount());
        Dragon d2 = new Dragon();
        assertEquals(4+monsterNumber,Monster.getMonsterCount());
        assertNotEquals(3+monsterNumber,Monster.getMonsterCount());
    }

    @Test
    void eat() {

        List<String> foods = new ArrayList<>();
        foods.add(0,"cookie");
        foods.add(1,"peasants");
        foods.add(2,"avocado");

        assertTrue(c.eat(foods.get(0)));
        assertFalse(c.eat(foods.get(1)));
        assertFalse(c.eat(foods.get(2)));
//        Testing Dragons
        assertFalse(d.eat(foods.get(0)));
        assertTrue(d.eat(foods.get(1)));
        assertFalse(d.eat(foods.get(2)));
    }

    @Test
    void sing() {
        assertFalse(c.performAction(10));
        assertTrue(c.performAction(7));
    }

    @Test
    void flap() {
        assertFalse(d.performAction(51));
        assertTrue(d.performAction(9));
        assertTrue(d.performAction(4));
    }

    @Test
    void badSing(){
        Action sing = new Sing(d);
        d.setAction(sing);
        assertFalse(d.performAction(51));
        assertFalse(d.performAction(9));
        assertFalse(d.performAction(4));
    }

    @Test
    void badFlap() {
        Action flap = new Flap(c);
        c.setAction(flap);
        assertFalse(c.performAction(10));
        assertFalse(c.performAction(7));
    }


}