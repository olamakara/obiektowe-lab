package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Vector2dTest {

    @Test
    void testIfObjectsEqual() {
        Vector2d vector = new Vector2d(4, 5);
        Object other_true = new Vector2d(4, 5);
        Object other_false = new Vector2d(-4, 5);
        Object other_diff_type = "wrong";

        var result1 = vector.equals(other_false);
        var result2 = vector.equals(other_true);
        var result3 = vector.equals(other_diff_type);

        assertFalse(result1);
        assertTrue(result2);
        assertFalse(result3);
    }

    @Test
    void testIfChangedToString() {

        Vector2d vector1 = new Vector2d(-1, 2);
        Vector2d vector2 = new Vector2d(3, -10);

        assertEquals("(-1,2)", vector1.toString());
        assertEquals("(3,-10)", vector2.toString());
    }

    @Test
    void testIfVectorPrecedesOther() {
        Vector2d vector = new Vector2d(-3, -8);
        Vector2d other_true = new Vector2d(5, -7);
        Vector2d other_false = new Vector2d(-3, -10);
        Vector2d other_same = new Vector2d(-3, -8);

        assertTrue(vector.precedes(other_true));
        assertFalse(vector.precedes(other_false));
        assertTrue(vector.precedes(other_same));
    }

    @Test
    void testIfVectorFollowsOther() {
        Vector2d vector = new Vector2d(-3, -8);
        Vector2d other_false = new Vector2d(5, -7);
        Vector2d other_true = new Vector2d(-3, -10);
        Vector2d other_same = new Vector2d(-3, -8);

        assertTrue(vector.follows(other_true));
        assertFalse(vector.follows(other_false));
        assertTrue(vector.follows(other_same));
    }

    @Test
    void testShouldGiveUpperRightOfVectors() {
        Vector2d vector = new Vector2d(-3, 8);
        Vector2d other1 = new Vector2d(5, -7);
        Vector2d other2 = new Vector2d(-3, 60);

        Vector2d upper_right1 = vector.upperRight(other1);
        Vector2d upper_right2 = vector.upperRight(other2);

        assertEquals(upper_right1.x, 5);
        assertEquals(upper_right1.y, 8);
        assertEquals(upper_right2.x, -3);
        assertEquals(upper_right2.y, 60);
    }

    @Test
    void testShouldGiveLowerLeftOfVectors() {
        Vector2d vector = new Vector2d(-3, 8);
        Vector2d other1 = new Vector2d(5, -7);
        Vector2d other2 = new Vector2d(-3, -60);

        Vector2d lower_left1 = vector.lowerLeft(other1);
        Vector2d lower_left2 = vector.lowerLeft(other2);

        assertEquals(lower_left1.x, -3);
        assertEquals(lower_left1.y, -7);
        assertEquals(lower_left2.x, -3);
        assertEquals(lower_left2.y, -60);
    }

    @Test
    void testShouldKnowVectorPlusOther() {
        Vector2d vector = new Vector2d(-3, 8);
        Vector2d other1 = new Vector2d(5, -7);
        Vector2d other2 = new Vector2d(-51, -61);

        Vector2d vector_plus_other1 = vector.add(other1);
        Vector2d vector_plus_other2 = vector.add(other2);

        assertEquals(vector_plus_other1.x, 2);
        assertEquals(vector_plus_other1.y, 1);
        assertEquals(vector_plus_other2.x, -54);
        assertEquals(vector_plus_other2.y, -53);
    }

    @Test
    void testShouldKnowVectorMinusOther() {
        Vector2d vector = new Vector2d(-3, 8);
        Vector2d other1 = new Vector2d(5, -7);
        Vector2d other2 = new Vector2d(-51, -61);

        Vector2d vector_minus_other1 = vector.subtract(other1);
        Vector2d vector_minus_other2 = vector.subtract(other2);

        assertEquals(vector_minus_other1.x, -8);
        assertEquals(vector_minus_other1.y, 15);
        assertEquals(vector_minus_other2.x, 48);
        assertEquals(vector_minus_other2.y, 69);
    }

    @Test
    void testShouldGiveOppositeVector() {
        Vector2d vector1 = new Vector2d(3, -6);
        Vector2d vector2 = new Vector2d(-51, -61);

        Vector2d opposite_vector1 = vector1.opposite();
        Vector2d opposite_vector2 = vector2.opposite();

        assertEquals(opposite_vector1.x, -3);
        assertEquals(opposite_vector1.y, 6);
        assertEquals(opposite_vector2.x, 51);
        assertEquals(opposite_vector2.y, 61);
    }

}
