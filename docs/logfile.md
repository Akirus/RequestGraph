# [Draft] Log file format

---
Version: 0.1 (1)

The semantic versioning is used, the value in the round brackets describes version in the logfile

---

The log file has a binary format because it's vital to have as smallest file as possible.

Sections below describe the format specifically to each field.

## Header
|Field|Description|Offset|Size (in bytes)|sample|
|---|---|---|---|---|
|Magic|A constant magic to determine a log file. The value is always: 0xDFE |0x0|3|0xDFE|
|Version|An integer representing the version of the format used for file|0x3 |4|1|
|Final|A boolean field, which represent if file is already final|0x7|1|1 or 0|
|_Unspecified_|A 10 bytes reserved for future needs|0x8|10|0|

## Record
After a header, file has an unspecified amount of records each records goes as following:
An offset for each record starts right after the previus record or header.

|Field|Description|Offset|Size (in bytes)|sample|
|---|---|---|---|---|
|Timestamp|A timestamp when event has occured|0x0|8|1541937291|
|Node Id|A node id which occured event|0x8|4|1|
|Receiver Id|A node id of the receiver|0xC|4|2|
|Wide Request id|A request id which comes through the whole system|0x10|8|12345|
|Payload size|a size of the payload field|0x18|4|12345|
|Payload|A user defined text which could be used for any type of user's data|0x1C|Any|{name: "Payment request", status: "Received"}|

Disregarding payload each records is 28 bytes size, which allows to contain 1 000 000 records in ~26 MB file.