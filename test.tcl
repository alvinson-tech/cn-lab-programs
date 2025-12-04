set ns [new Simulator]
set nf [open PA1.nam w]
$ns namtrace-all $nf
set tf [open PA1.tr w]
$ns trace-all $tf

proc finish {} {
    global ns nf tf
    $ns flush-trace
    close $nf
    close $tf
    exec nam PA1.nam &
    exit 0
}

set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]
set n3 [$ns node]
set n4 [$ns node]
set n5 [$ns node]

$ns duplex-link $n0 $n4 1005Mb 1ms DropTail
$ns duplex-link $n1 $n4 50Mb 1ms DropTail
$ns duplex-link $n2 $n4 2000Mb 1ms DropTail
$ns duplex-link $n3 $n4 200Mb 1ms DropTail
$ns duplex-link $n4 $n5 1Mb 1ms DropTail
$ns queue-limit $n0 $n4 5
$ns queue-limit $n2 $n4 3
$ns queue-limit $n4 $n5 2

set p1 [new Agent/Ping]
set p2 [new Agent/Ping]
set p3 [new Agent/Ping]
set p4 [new Agent/Ping]
set p5 [new Agent/Ping]

$ns attach-agent $n0 $p1
$ns attach-agent $n1 $p2
$ns attach-agent $n2 $p3
$ns attach-agent $n3 $p4
$ns attach-agent $n5 $p5

$p1 set packetSize_ 50000
$p1 set interval_ 0.0001

$p3 set packetSize_ 30000
$p3 set interval_ 0.00001

$ns connect $p1 $p5
$ns connect $p3 $p4

Agent/Ping instproc recv {from rtt} {
    puts "RTT: $rtt ms"
}

for {set i 0.1} {$i <= 2.9} {set i [expr $i + 0.1]} {
    $ns at $i "$p1 send"
}

for {set i 0.1} {$i <= 2.9} {set i [expr $i + 0.1]} {
    $ns at $i "$p3 send"
}

$ns at 3.0 "finish"
$ns run