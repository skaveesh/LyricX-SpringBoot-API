package com.lyricxinc.lyricx.core.util;

import com.lyricxinc.lyricx.core.constant.Constants;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.function.BiFunction;

/**
 * The type Batch insert.
 *
 * @param <T> the type parameter
 * @param <S> the type parameter
 * @param <R> the type parameter
 */
public class BatchInsert<T, S, R> {

    /**
     * Process.
     *
     * @param s             the s
     * @param tList         the t list
     * @param entityManager the entity manager
     * @param strBiFunction the str bi function
     */
    public void process(S s, List<T> tList, EntityManager entityManager, BiFunction<T, S, R> strBiFunction) {

        if (s == null || tList == null) {
            return;
        }

        for (int i = 0; i < tList.size(); i++) {
            if (i > 0 && i % Constants.AppConstants.BATCH_SIZE == 0) {
                entityManager.flush();
                entityManager.clear();
            }
            if (tList.get(i) != null) {
                R r = strBiFunction.apply(tList.get(i), s);
                entityManager.persist(r);
            }
            if (tList.size() - 1 == i) {
                entityManager.flush();
                entityManager.clear();
            }
        }
    }

}
