MACHINE Interlocking
SEES
    Context, Topology, ControlTable, ReleaseTable
SETS
    ANSWER = {yes,no}


VARIABLES
    pos, nextd, signalStatus,
    normalPoints, reversePoints,
    currentPointLocks, currentRouteLocks,
    derails, runthru


DEFINITIONS

    Collision(t) == #(t1,t2).(t1 : TRAIN & t2 : TRAIN & t1 /= t2 &
            (dom({pos(t1)}) = {t})  &
            (dom({pos(t2)}) = {t}));

    Derailment(t) == t : derails;

    RunThrough(t) == runthru = t ;

    SafeAt(t) == not(Collision(t)) & not(Derailment(t)) & not(RunThrough(t));

    Unsafe == #t.(t : (dom(ran(pos)) \/ derails \/ {runthru}) /\ CRITICAL & not(SafeAt(t)));

    auxnextd(np,rp) == {
        (p1 |-> p2) | #(t1,d1,t2,d2).
           (p1 = (t1,d1) & p2 = (t2,d2) &
            (((t1,d1) : EXIT & p2 = (offUnit,(0,0))) or
             (t1 /= t2 & ran({d1}) = dom({d2}))) &  /* to(d1) == from(d2) */
            d1 : directedUnits[{t1}] &
            d1 : staticDirection \/
            dynamicDirection[np*{normal} \/
                             rp*{reverse}] &
            d2 : directedUnits[{t2}] &
            d2 : staticDirection \/
            dynamicDirection[np*{normal} \/
                             rp*{reverse}])
    };

    freeEntries ==    { e | e : ENTRY & entryTable[{e}] /\ dom(ran(pos)) = {} }
                   /\ { e | e : ENTRY & dom({e}) <| currentRouteLocks <: {e} }


INVARIANT
    pos : TRAIN
          --> directedUnits &

    nextd :
        UNIT*(CONNECTOR*CONNECTOR)
        +-> UNIT*(CONNECTOR*CONNECTOR) &

    signalStatus : SIGNAL --> SIGNALSTATUS &

    normalPoints <: POINT &
    reversePoints <: POINT &
    normalPoints /\ reversePoints = {} &
    normalPoints \/ reversePoints = POINT &

    currentPointLocks  : ROUTE <-> POINT &
    currentPointLocks <: lockTable &
    currentRouteLocks <: directedUnits &

    not(Unsafe)
    &
    derails <: UNIT
    &
    runthru : UNIT

INITIALISATION
BEGIN
    pos := TRAIN * {(offUnit,(0,0))} ||
    nextd := auxnextd(POINT, {}) ||
    signalStatus := SIGNAL * {red} ||
    normalPoints := POINT ||
    reversePoints := {} ||
    currentPointLocks := {} ||
    currentRouteLocks := {} ||
    derails := {} ||
    runthru := nullUnit
END

OPERATIONS

collide  =         SELECT #t.(t : dom(ran(pos)) - {nullUnit,offUnit} & Collision(t))     THEN skip END;
derail =           SELECT #t.(t : derails       - {nullUnit,offUnit} & Derailment(t))    THEN skip END;
runThrough =       SELECT #t.(t : {runthru}     - {nullUnit,offUnit} & RunThrough(t))    THEN skip END;

s <-- nextSignal(t,cp) =
SELECT not(Unsafe) THEN
  PRE t : TRAIN & t : dom(pos)  & cp : UNIT & cp : dom({pos(t)})
  THEN
    IF pos(t) : ran(homeSignal)
    THEN
      s := signalStatus(homeSignal~(pos(t)))
    ELSE
      s := nosignal
    END
  END
END;

bb <-- request(route) =
SELECT not(Unsafe) THEN
  PRE route : ROUTE THEN
      LET occTracks,emptyTracks,unlockedPoints BE
          occTracks =  dom(ran(pos)) &
          emptyTracks = UNIT - occTracks &
          unlockedPoints = POINT - ran(currentPointLocks)
      IN
          IF  (signalStatus(signal(route)) = red) &
              (clearTable[{route}] <: emptyTracks ) &
              ((clearTable[{route}] <| currentRouteLocks) <: directedTable[{route}]) &
              (normalTable[{route}]  <: normalPoints  \/ unlockedPoints) &
              (reverseTable[{route}] <: reversePoints \/ unlockedPoints)
          THEN
              VAR np, rp, movedPoints IN
                  np := (normalPoints \/ normalTable[{route}]) - reverseTable[{route}]  ;
                  rp := (reversePoints \/ reverseTable[{route}]) - normalTable[{route}] ;
                  movedPoints := (np - normalPoints) \/ (rp - reversePoints) ;
                  normalPoints := np ||
                  reversePoints := rp ||
                  derails := { h | #(t,p).(h : UNIT & p : movedPoints & t : TRAIN & h= homePoint(p) & h : dom({pos(t)})) } ||
                  nextd := auxnextd(np,rp) ||
                  currentPointLocks := currentPointLocks \/ ({route} <| lockTable) ||
                  currentRouteLocks := currentRouteLocks \/ (directedTable[{route}]) ||
                  signalStatus(signal(route)) := green ||
                  bb := yes
              END
          ELSE
              bb:= no
          END /* end if */
      END /* end let */
  END /* end pre */
END;

bb <-- hiddenRequest(route) =
SELECT not(Unsafe) THEN
  PRE route : HIDDENROUTE THEN
      LET occTracks,emptyTracks,unlockedPoints BE
          occTracks =  dom(ran(pos)) &
          emptyTracks = UNIT - occTracks &
          unlockedPoints = POINT - ran(currentPointLocks)
      IN
          IF   (clearTable[{route}] <: emptyTracks ) &
              ((clearTable[{route}] <| currentRouteLocks) <: directedTable[{route}]) &
              (normalTable[{route}]  <: normalPoints  \/ unlockedPoints) &
              (reverseTable[{route}] <: reversePoints \/ unlockedPoints)
          THEN
              VAR np, rp, movedPoints IN
                  np := (normalPoints \/ normalTable[{route}]) - reverseTable[{route}]  ;
                  rp := (reversePoints \/ reverseTable[{route}]) - normalTable[{route}] ;
                  movedPoints := (np - normalPoints) \/ (rp - reversePoints) ;
                  normalPoints := np ||
                  reversePoints := rp ||
                  derails := { h | #(t,p).(h : UNIT & p : movedPoints & t : TRAIN & h= homePoint(p) & h : dom({pos(t)})) } ||
                  nextd := auxnextd(np,rp) ||
                  bb := yes
              END
          ELSE
              bb:= no
          END /* end if */
      END /* end let */
  END /* end pre */
END;

newp <-- move(t,currp) =
SELECT not(Unsafe) & (currp = offUnit => freeEntries /= {}) THEN
  PRE t : TRAIN & currp : dom({pos(t)})
  THEN
    IF currp = offUnit THEN
      ANY (track,d) WHERE (track,d) : freeEntries THEN
        pos(t) := (track,d) ||
        newp := track
      END
    ELSE
      IF (pos(t) /: dom(nextd)) THEN
        pos(t) := (nullUnit,(-1,-1)) ||
        newp := nullUnit  ||
        runthru := staticNext(pos(t))
      ELSE
        LET (track,d) BE (track,d) = nextd(pos(t)) IN
          pos(t) := (track,d) ||
          newp := track ||
          currentPointLocks := currentPointLocks - releaseTable[dom({pos(t)})] ||
          currentRouteLocks :=  currentRouteLocks - {pos(t)} ||
          IF (pos(t) : ran(homeSignal) &
              signalStatus(homeSignal~(pos(t))) = green) THEN
            signalStatus(homeSignal~(pos(t))) := red
          END
        END
      END
    END
  END
END;

changeDirection(t,currp) =
SELECT not(Unsafe) THEN
  PRE t : TRAIN & t : dom(pos) & currp : dom({pos(t)}) &  currp : TERMINAL
  THEN
    LET (track,d) BE (track,d) = pos(t) IN
      LET (d1,d2) BE (d1,d2) = d IN
        pos(t) := (track,(d2,d1)) ||
        currentRouteLocks := currentRouteLocks - {track |-> (d1,d2)}
      END
    END
  END
END;

bb <-- release(route) =
SELECT not(Unsafe) THEN
  PRE route : ROUTE THEN
    LET emptyTracks BE
    emptyTracks = UNIT - dom(ran(pos)) IN
      IF
        (signalStatus(signal(route)) = green)  &
        currentPointLocks[{route}] = lockTable[{route}] &
        dom({homeSignal(signal(route))}) <: emptyTracks
      THEN
        signalStatus(signal(route)) := red ||
        currentPointLocks := {route} <<| currentPointLocks ||
        currentRouteLocks := currentRouteLocks - directedTable[{route}] ||
        bb := yes
      ELSE
        bb := no
      END
    END /* let */
  END
END

END
