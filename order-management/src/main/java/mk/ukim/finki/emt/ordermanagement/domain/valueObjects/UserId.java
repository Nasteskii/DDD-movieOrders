package mk.ukim.finki.emt.ordermanagement.domain.valueObjects;

import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;

public class UserId extends DomainObjectId {
    protected UserId() {
        super(UserId.randomId(UserId.class).getId());
    }

    public UserId(String uuid) {
        super(uuid);
    }
}
