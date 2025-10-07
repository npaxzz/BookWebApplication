let localStream;
const peers = {};
const ws = new WebSocket(`ws://${window.location.host}/ws/call`);

async function joinCall() {
    localStream = await navigator.mediaDevices.getUserMedia({ audio: true });
    const localAudio = document.createElement("audio");
    localAudio.srcObject = localStream;
    localAudio.autoplay = true;
    document.getElementById("peersContainer").appendChild(localAudio);

    ws.onmessage = async (event) => {
        const msg = JSON.parse(event.data);
        if(msg.from === username) return; // ignore self

        if(msg.type === "offer") {
            const pc = createPeer(msg.from);
            await pc.setRemoteDescription(new RTCSessionDescription(msg.sdp));
            const answer = await pc.createAnswer();
            await pc.setLocalDescription(answer);
            ws.send(JSON.stringify({ roomId, from: username, to: msg.from, type: "answer", sdp: answer }));
        } else if(msg.type === "answer") {
            await peers[msg.from].setRemoteDescription(new RTCSessionDescription(msg.sdp));
        } else if(msg.type === "candidate") {
            await peers[msg.from].addIceCandidate(new RTCIceCandidate(msg.candidate));
        }
    };
}

function createPeer(peerName) {
    const pc = new RTCPeerConnection();
    localStream.getTracks().forEach(track => pc.addTrack(track, localStream));

    pc.ontrack = e => {
        const audio = document.createElement("audio");
        audio.srcObject = e.streams[0];
        audio.autoplay = true;
        document.getElementById("peersContainer").appendChild(audio);
    };

    pc.onicecandidate = e => {
        if(e.candidate) {
            ws.send(JSON.stringify({ roomId, from: username, to: peerName, type: "candidate", candidate: e.candidate }));
        }
    };

    peers[peerName] = pc;
    return pc;
}

document.getElementById("joinBtn").onclick = joinCall;
document.getElementById("leaveBtn").onclick = () => location.reload();
