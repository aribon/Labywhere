package me.aribon.labywhere.backend.utils;

import android.support.annotation.Nullable;
import android.telephony.SubscriptionManager;

import java.util.HashMap;
import java.util.Map;

import rx.Subscription;

/**
 * Created by aribon from Insign Mobility
 * on 11/10/2016
 */
public class SubscriptionCollector {

    private static final String TAG = SubscriptionCollector.class.getSimpleName();

    private static SubscriptionCollector instance = new SubscriptionCollector();

    private Map<Long, Subscription> subscriptionCollector;

    public static void initialize() {
        instance = new SubscriptionCollector();
    }

    public static SubscriptionCollector getInstance() {
        if (instance == null)
            instance = new SubscriptionCollector();
        return instance;
    }

    private SubscriptionCollector() {
        subscriptionCollector = new HashMap<>();
    }

    private long generateId() {
        return System.currentTimeMillis();
    }

    public long addSubscription(Subscription subscription) {
        if (hasSubscription(subscription)) {
            return getId(subscription);
        } else {
            long id;

            do id = generateId();
            while (id <= 0 && hasSubscription(id));
            subscriptionCollector.put(id, subscription);
            return id;
        }
    }

    public boolean hasSubscription (long id) {
        return subscriptionCollector.containsKey(id);
    }

    public boolean hasSubscription (Subscription subscription) {
        return subscriptionCollector.containsValue(subscription);
    }

    public long getId(Subscription subscription) {
        if (hasSubscription(subscription)) {
            Map<Long, Subscription> tmpSubscriptionCollector = new HashMap<>(subscriptionCollector);

            for (Map.Entry<Long, Subscription> entry : tmpSubscriptionCollector.entrySet()) {
                if (entry != null) {
                    if (entry.getValue() != null && entry.getValue().equals(subscription)) {
                        return entry.getKey();
                    }
                }
            }
        }

        return 0;
    }

    public @Nullable Subscription getSubscription(long id) {
        if (hasSubscription(id)) {
            return subscriptionCollector.get(id);
        }

        return null;
    }

    public void removeSubscription(long id) {
        if (hasSubscription(id)) {
            subscriptionCollector.remove(id);
        }
    }

    public void removeSubscription(Subscription subscription) {
        if (hasSubscription(subscription) && !subscription.isUnsubscribed()) {
            Map<Long, Subscription> tmpSubscriptionCollector = new HashMap<>(subscriptionCollector);

            for (Map.Entry<Long, Subscription> entry : tmpSubscriptionCollector.entrySet()) {
                if (entry != null) {
                    if (entry.getValue() != null && entry.getValue().equals(subscription))
                        entry.getValue().unsubscribe();
                    subscriptionCollector.remove(entry.getKey());
                }
            }
        }
    }

    public void update() {
        Map<Long, Subscription> tmpSubscriptionCollector = new HashMap<>(subscriptionCollector);

        for (Map.Entry<Long, Subscription> entry : tmpSubscriptionCollector.entrySet()) {
            if (entry != null) {
                if (entry.getValue() != null && entry.getValue().isUnsubscribed())
                    subscriptionCollector.remove(entry.getKey());
            }
        }
    }

    public void clear() {
        Map<Long, Subscription> tmpSubscriptionCollector = new HashMap<>(subscriptionCollector);

        for (Map.Entry<Long, Subscription> entry : tmpSubscriptionCollector.entrySet()) {
            if (entry != null) {
                if (entry.getValue() != null && !entry.getValue().isUnsubscribed())
                    entry.getValue().unsubscribe();
                subscriptionCollector.remove(entry.getKey());
            }
        }

        subscriptionCollector.clear();
    }
}
