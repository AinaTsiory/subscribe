package school.hei.subscribe.endpoint.event.consumer.model;

import school.hei.subscribe.PojaGenerated;
import school.hei.subscribe.endpoint.event.model.PojaEvent;

@PojaGenerated
public record TypedEvent(String typeName, PojaEvent payload) {}
