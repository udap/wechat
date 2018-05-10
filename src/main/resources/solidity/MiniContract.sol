pragma solidity ^0.4.0;

contract MiniContract {

    address delegate;

    //constructor () public {
    function MiniContract() public {
        delegate = msg.sender;
    }

    modifier access() {
        if (msg.sender != delegate) {
            //revert("delegate wrong");
            revert();
        }
        _;
    }

    struct minicontract {
        bytes A;
        bytes B;
        bool finished;
        bytes title;
        bytes content;
        bytes soundAddr;
        bytes pictureAddr;
    }

    mapping(uint256 => minicontract) db;
    uint256 dbindex;//index to last Non-Used cardinal number

    mapping(bytes => uint256[]) owns;

    event eMint(uint256 dbindex);
    event eComplete(uint256 dbindex);

    function mint(bytes _A, bytes _B, bytes _title, bytes _content, bytes _sound, bytes _picture) access public {
        minicontract memory temp = minicontract(_A, _B, false, _title, _content, _sound, _picture);
        db[dbindex] = temp;

        owns[_A].push(dbindex);
        owns[_B].push(dbindex);
        //emit eMint(dbindex);
        eMint(dbindex);

        dbindex++;
    }

    function complete(uint256 _dbindex) access public {
        db[_dbindex].finished = true;
        //emit eComplete(_dbindex);
        eComplete(_dbindex);
    }

    function list(bytes _owner) public view returns (uint256[]){
        return owns[_owner];
    }

    function detail(uint256 _dbindex) public view returns (bytes _A, bytes _B, bool _finished, bytes _title, bytes _content, bytes _sound, bytes _picture){
        _A = db[_dbindex].A;
        _B = db[_dbindex].B;
        _finished = db[_dbindex].finished;
        _title = db[_dbindex].title;
        _content = db[_dbindex].content;
        _sound = db[_dbindex].soundAddr;
        _picture = db[_dbindex].pictureAddr;
    }
}