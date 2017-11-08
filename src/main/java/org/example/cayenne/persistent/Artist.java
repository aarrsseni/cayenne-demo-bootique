package org.example.cayenne.persistent;

import org.apache.cayenne.cache.invalidation.CacheGroups;
import org.apache.cayenne.commitlog.CommitLog;
import org.example.cayenne.persistent.auto._Artist;

@CommitLog
@CacheGroups("artist-group")
public class Artist extends _Artist {

    private static final long serialVersionUID = 1L; 

}
