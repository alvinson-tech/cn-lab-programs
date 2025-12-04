BEGIN {
    drop = 0;
}
{
    if ($1 == "d") {
        drop++;
    }
}
END {
    printf("Total packets dropped due to congestion = %d\n", drop);
} 