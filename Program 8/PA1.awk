BEGIN {
    dropped = 0;
}
{
    if ($1 == "d") {
        dropped++;
        printf("%s\t%s\n", $5, $11);
    }
}
END {
    printf("Total packets dropped = %d\n", dropped);
}