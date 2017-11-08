package org.apache.cayenne.demo;

import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.commitlog.CommitLogListener;
import org.apache.cayenne.commitlog.model.ChangeMap;
import org.apache.cayenne.commitlog.model.ObjectChangeType;

public class MyCommitLogListner implements CommitLogListener {
    @Override
    public void onPostCommit(ObjectContext objectContext, ChangeMap changes) {
        changes.getUniqueChanges().stream()
                .filter(change -> change.getType() == ObjectChangeType.INSERT)
                .flatMap(change -> change.getAttributeChanges().entrySet().stream())
                .forEach(id -> System.out.println("Inserted new entity with name: " + id.getValue().getNewValue()));
    }
}
